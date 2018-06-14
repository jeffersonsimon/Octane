import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;

import com.hpe.adm.nga.sdk.Octane;
import com.hpe.adm.nga.sdk.authentication.Authentication;
import com.hpe.adm.nga.sdk.authentication.SimpleClientAuthentication;
import com.hpe.adm.nga.sdk.entities.EntityList;
import com.hpe.adm.nga.sdk.entities.OctaneCollection;
import com.hpe.adm.nga.sdk.entities.get.GetEntities;
import com.hpe.adm.nga.sdk.model.EntityModel;
import com.hpe.adm.nga.sdk.model.FieldModel;
import com.hpe.adm.nga.sdk.model.LongFieldModel;
import com.hpe.adm.nga.sdk.model.ReferenceFieldModel;
import com.hpe.adm.nga.sdk.model.StringFieldModel;

import utils.CommonUtils;
import utils.generator.DataGenerator;

import com.hpe.adm.nga.sdk.entities.create.CreateEntities;

public class uploader {
	static Octane octane;
	static String ENTITY;
	static EntityList entityList;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//Mention the Client ID and Client Secret which can be created from the API Access tab in Octane
        Authentication clientAuthentication = new SimpleClientAuthentication("JavaRESTAPInew_0e9jpjlvzdmngt22r5yvyqvzg", ")50cca34f644aa286O");

        // 2) User/pass
        //Authentication userPassAuthentication = new SimpleUserAuthentication("user", "password");

        // get instance of Octane Builder
         Octane.Builder octaneBuilder = new Octane.Builder(clientAuthentication);

        // now we can add the server
        octaneBuilder.Server("https://almoctane-eur.saas.hpe.com/"); //mention server with port here
        // the sharedspace
        octaneBuilder.sharedSpace(124004); //mention shared workspace here
        // the workspace
        octaneBuilder.workSpace(2001); //mention workspace here

        // finally we build the context and get an Octane instance:

        octane = octaneBuilder.build();
         ENTITY = "requirement_folders "; // you can mention any entity you want here like user stories, requirements, etc
        
        entityList = octane.entityList(ENTITY);
        
     
        new uploader().testCreateEntity();
         
	}
	
	  public void testCreateEntity() throws Exception {
		  String entityName=ENTITY;
	        Collection<EntityModel> generatedEntity = DataGenerator.generateEntityModel(octane, entityName);
	        Collection<EntityModel> entityModels = entityList.create().entities(generatedEntity).execute();
	        EntityModel entityModel = entityModels.iterator().next();
	        String entityId = CommonUtils.getIdFromEntityModel(entityModel);
System.out.println(entityId);
	      //  EntityModel getEntity = entityList.at(entityId).get().execute();

	        
	    }

}
