# sfci-shared-lib-health
# Basic REST Request to communicate elastic instance
    1. Create a new index named health
        curl -XPUT http://esearch-sfcies.eng.sfdc.net:9200/health?pretty=true -d ' {
            "settings": {
                "index": {
                    "number_of_replicas": 1,
                    "number_of_shards": 5
                }
            }
        }'
    2. Map Index 
        curl -XPUT http://esearch-sfcies.eng.sfdc.net:9200/health/_mapping/health?pretty=true -d '{
                    "health": {
                        "properties": {
                            "run_source": {
                                "type": "string"
                            },
                            "stage_name": {
                                "type": "string"
                            },
                            "step_name": {
                                "type": "string"
                            },
                            "status": {
                                "type": "string"
                            },
                            "timeTaken": {
                                "type": "string"
                            },
                            "timestamp": {
                                "type": "string"
                            }
                        }
                    }
                }'
    3. Sample Data insertion on index health
        curl -XPOST http://esearch-sfcies.eng.sfdc.net:9200/health/health?pretty=true -d ' {
            "stage_name": "maven-docker",
            "step_name": "mavenInit",
            "status": "true",
            "timestamp": "1510274776"
        } '
    4. Delete all documents of type health inside index health
        curl -XDELETE 'http://esearch-sfcies.eng.sfdc.net:9200/health/health/_query' -d '{
            "query" : { 
                "match_all" : {}
            }
        }'
    5. Fetch all data from index starting with name builds* with masterName as "sconelibci.dop.sfdc.net" and output to save in file out.json
        curl -X GET -i 'http://esearch-sfcies.eng.sfdc.net:9200/builds-*/_search/?size=100000' --data '{
           "query":{
              "term":{
                 "masterName":"sconelibci.dop.sfdc.net"
              }
           }
        }' -o out.json
    6. Fetch only selected attributes from index metrics-hourly-* with masterName as "sconelibci.dop.sfdc.net" and save in file hourly-data.json
        curl -X POST -i 'http://esearch-sfcies.eng.sfdc.net:9200/metrics-hourly-*/_search?size=100000' --data '{
           "_source":[
              "@timestamp",
              "masterName_analyzed",
              "system.cpu.load.value",
              "vm.blocked.count.value",
              "vm.count.value",
              "vm.cpu.load.value",
              "vm.daemon.count.value",
              "vm.deadlocks.value",
              "vm.file.descriptor.ratio.value",
              "vm.gc..count.value",
              "vm.gc..time.value",
              "vm.memory.heap.committed.value" 
           ],
           "query":{
              "term":{
                 "masterName_analyzed":"sconelibci.dop.sfdc.net"
              }
           }
        }' -o hourly-data.json
    7. Search all document inside index health 
        http://esearch-sfcies.eng.sfdc.net:9200/health/_search/?size=5
    8. List all index on instance 
        http://esearch-sfcies.eng.sfdc.net:9200/_cat/indices?v
