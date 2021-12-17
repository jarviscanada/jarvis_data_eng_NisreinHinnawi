#!/bin/bash

#assign CLI arguments to variables
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

#check number of arguments
if [ $# -ne 5 ]; then
    echo "Invalid Number of Arguments given"
    exit 1;
fi

#Save machine statistics in MB and current machine hostname to variables
vmstat_mb=$(vmstat --unit M)
hostname=$(hostname -f)

#parse host hardware specifications using bash cmds
memory_free=$(echo "$vmstat_mb" | awk '{print $4}'| tail -n1 | xargs)
cpu_idle=$(echo "$vmstat_mb" | egrep -v 'cpu|id' | awk '{ print $15 }' | xargs)
cpu_kernel=$(echo "$vmstat_mb" | egrep -v 'cpu|id' | awk '{ print $14 }' | xargs)
disk_io=$(vmstat -d | awk '{print $10}' | tail -1 | xargs)
disk_available=$(df -BM / | egrep "^/dev/sda2" | awk '{print $4}' | sed 's/.$//' | xargs)
timestamp=$(vmstat -t | tail -1 | awk '{print $18 " " $19}' | xargs)

#Subquery to find matching id in host_info table
host_id="(SELECT id FROM host_info WHERE hostname='$hostname')";

#Inserts server usage data into host_usage table
insert_stmt="INSERT INTO host_usage (timestamp, host_id, memory_free, cpu_idle, cpu_kernel, disk_io, disk_available)
VALUES ('$timestamp', (SELECT id FROM host_info WHERE hostname='$host_name'), '$memory_free', '$cpu_idle', '$cpu_kernel',
'$disk_io', '$disk_available');"

#set up env var for pql cmd
export PGPASSWORD=$psql_password

#Insert date into a database
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"

exit $?












