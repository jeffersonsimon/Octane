/*
 * Copyright 2017 Hewlett-Packard Enterprise Development Company, L.P.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package utils;

import com.hpe.adm.nga.sdk.query.Query;
import com.hpe.adm.nga.sdk.query.QueryMethod;

import java.util.List;

/**
 *
 * Created by Guy Guetta on 21/04/2016.
 */
public class QueryUtils {
    public static Query getQueryForIds(List<String> entityIds) {

        Query.QueryBuilder queryBuilder = null;

        for (int i = 0; i < entityIds.size(); i++) {
            if (queryBuilder == null) {
                queryBuilder = Query.statement("id", QueryMethod.EqualTo, entityIds.get(i));
            } else {
                queryBuilder.or("id", QueryMethod.EqualTo, entityIds.get(i));
            }
        }
        return queryBuilder.build();
    }
}
