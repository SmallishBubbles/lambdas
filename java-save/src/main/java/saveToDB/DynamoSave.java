/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package saveToDB;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;


public class DynamoSave {

    private DynamoDB dynamoDb;
    private String DYNAMODB_TABLE_NAME = "taskmaster-backend";
    private Regions REGION = Regions.US_WEST_2;

    public Task saveToDB(Task task, Context context){
        if ( task.getId() != null && task.getId().equals("warmer") ) {
            return new Task();
        }

        Task newTask = new Task(task.getId(), task.getTitle(), task.getDescription(), "Available", "null", "null");

        newTask.addHistory(new HistoryObj("Task created"));

        final AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.defaultClient();
        DynamoDBMapper ddbMapper = new DynamoDBMapper(ddb);

        ddbMapper.save(newTask);

        return newTask;
    }


}
