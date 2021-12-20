
--create function that round timestamp to the nearest 5-minute
CREATE FUNCTION round5(ts timestamp) RETURNS timestamp AS
$$
BEGIN
    RETURN date_trunc('hour', ts) + date_part('minute', ts):: int / 5 * interval '5 min';
END;
$$
    LANGUAGE PLPGSQL;


--query to group hosts by CPU number and sort by their memory size in descending order
SELECT
    cpu_number,
    id AS host_id,
    total_mem
FROM host_info
ORDER BY cpu_number, total_mem DESC;


--query to calculate average used memory in percentage over 5 mins interval for each host_id
SELECT
    host_usage.host_id, host_info.hostname AS host_name,
    round5(host_usage.timestamp) AS timestamp,
    AVG(host_info.total_mem - host_usage.memory_free)/AVG(host_info.total_mem)*100 AS avg_used_mem_percentage
FROM host_ifo JOIN host_usage ON host_info.id = host_usage.host_id
GROUP BY timestamp, host_name, host_usage.host_id,
ORDER BY avg_used_mem_percentage;


--query to detect host failures.
SELECT
    host_id,
    round5(timestamp) AS timestamp,
    COUNT(host_id) AS num_data_points
FROM host_usage
GROUP BY host_id, timestamp
HAVING COUNT(host_id) < 3
ORDER BY host_id;