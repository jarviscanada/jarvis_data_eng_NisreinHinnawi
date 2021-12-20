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

#save hostname to a variable
hostname=$(hostname -f)

#save number of CPU to a variable
lscpu_out=`lscpu`

#parse host hardware specifications using bash cmds
cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(echo "$lscpu_out"  | egrep "^Architecture:" | awk '{print $2}' | xargs)
cpu_model=$(echo "$lscpu_out" | egrep "^Model\sname:" | awk '{$1=$2=""; print $0}' | xargs)
cpu_mhz=$(echo "$lscpu_out"  | egrep "CPU\sMHz:" | awk '{print $3}' | xargs)
l2_cache=$(echo "$lscpu_out" | egrep "^L2\scache:" | awk '{$1=$2=""; print $0}'| sed 's/.$//' | xargs)
total_mem=$(cat /proc/meminfo | egrep "^MemTotal:" | awk '{print $2}' | xargs)
timestamp=$(date +"%Y-%m-%d %T")

#Inserts  data into host_info table
insert_stmt="INSERT INTO host_info (hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, L2_cache, total_mem, timestamp)
VALUES ('$hostname', '$cpu_number', '$cpu_architecture', '$cpu_model', '$cpu_mhz', '$l2_cache', '$total_mem', '$timestamp');"

#set up env var for pql cmd
export PGPASSWORD=$psql_password

#Insert date into a database
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"

exit $?

#set up env var for pql cmd
export PGPASSWORD=$psql_password

#Insert date into a database
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"

exit $?

