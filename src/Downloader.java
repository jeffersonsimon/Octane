import java.util.Set;

import org.jsoup.Jsoup;

import com.hpe.adm.nga.sdk.Octane;
import com.hpe.adm.nga.sdk.authentication.Authentication;
import com.hpe.adm.nga.sdk.authentication.SimpleClientAuthentication;
import com.hpe.adm.nga.sdk.entities.EntityList;
import com.hpe.adm.nga.sdk.entities.OctaneCollection;
import com.hpe.adm.nga.sdk.entities.get.GetEntities;
import com.hpe.adm.nga.sdk.entities.get.GetEntity;
import com.hpe.adm.nga.sdk.model.EntityModel;
import com.hpe.adm.nga.sdk.model.FieldModel;
public class Downloader {

	public static void main(String[] args) {
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

        Octane octane = octaneBuilder.build();
        String ENTITY = "manual_tests"; // you can mention any entity you want here like user stories, requirements, etc
        EntityList entityList;
        entityList = octane.entityList(ENTITY);
        //get specific entities with ID
        // EntityList.Entities entity = entityList.at("2001");
        // we are going to use this to GET the entity
        // GetEntity get = entity.get();
         GetEntities get = entityList.get();
        // this actually executes the REST request and gets the entity
         OctaneCollection<EntityModel> entityModels = get.execute();
         FieldModel idField;
         FieldModel nameField;
         FieldModel DescriptionField;
         EntityModel entityModel;
         int ColSize=entityModels.size();
         for(int i=0;i<ColSize;i++)
         {
        	 entityModel= entityModels.iterator().next();
        	  idField = entityModel.getValue("id");
             // and get its value.  Currently this is an Object since the SDK is not type aware
             Object value_id = idField.getValue();
             
            // the entity model can now be manipulated.  There will be only one since we are getting just one entity
            // so for example we can get the name field
              nameField = entityModel.getValue("name");
             // and get its value.  Currently this is an Object since the SDK is not type aware
             Object value_name = nameField.getValue();
            
             DescriptionField = entityModel.getValue("description");
            // and get its value.  Currently this is an Object since the SDK is not type aware
            Object value_description = DescriptionField.getValue();
           System.out.println("ID: "+value_id+";"+" Name: "+value_name+";"+" Description: "+Jsoup.parse(value_description.toString()).text());
           entityModels.remove(entityModel);
           
         }
        
        
        
	}

}
