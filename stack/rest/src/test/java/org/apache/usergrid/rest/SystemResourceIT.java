/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.usergrid.rest;


import org.junit.Test;

import org.apache.usergrid.rest.test.resource.AbstractRestIT;
import org.apache.usergrid.rest.test.resource.model.Entity;
import org.apache.usergrid.rest.test.resource.model.QueryParameters;

import java.util.LinkedHashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * Tests endpoints that use /system/*
 */
public class SystemResourceIT extends AbstractRestIT {

    @Test
    public void testSystemDatabaseAlreadyRun() {
        QueryParameters queryParameters = new QueryParameters();
        queryParameters.addParam( "access_token", clientSetup.getSuperuserToken().getAccessToken() );

        Entity result = clientSetup.getRestClient().system().database().setup().put( queryParameters );

        assertNotNull( result );
        assertNotNull( "ok", ( String ) result.get( "status" ) );

        result = clientSetup.getRestClient().system().database().setup().put( queryParameters );

        assertNotNull( result );
        assertNotNull( "ok", ( String ) result.get( "status" ) );
    }

    @Test
    public void testDeleteAllApplicationEntities() {
        QueryParameters queryParameters = new QueryParameters();
        queryParameters.addParam( "access_token", clientSetup.getSuperuserToken().getAccessToken() );
        queryParameters.addParam("confirmApplicationId", this.clientSetup.getAppUuid());

        org.apache.usergrid.rest.test.resource.model.ApiResponse result = clientSetup.getRestClient().system().applications(this.clientSetup.getAppUuid()).delete( queryParameters);

        assertNotNull( result );
        assertNotNull( "ok",result.getStatus() );
        assertEquals(((LinkedHashMap) result.getData()).get("count"), 0);
    }


    @Test
    public void testBoostrapAlreadyRun() {
        QueryParameters queryParameters = new QueryParameters();
        queryParameters.addParam( "access_token", clientSetup.getSuperuserToken().getAccessToken() );

        Entity result = clientSetup.getRestClient().system().database().bootstrap().put( queryParameters );

        assertNotNull( result );
        assertNotNull( "ok", ( String ) result.get( "status" ) );

        result = clientSetup.getRestClient().system().database().bootstrap().put( queryParameters );

        assertNotNull( result );
        assertNotNull( "ok", ( String ) result.get( "status" ) );
    }
}
