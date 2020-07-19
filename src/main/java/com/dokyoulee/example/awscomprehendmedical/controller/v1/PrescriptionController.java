package com.dokyoulee.example.awscomprehendmedical.controller.v1;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.comprehendmedical.AWSComprehendMedical;
import com.amazonaws.services.comprehendmedical.AWSComprehendMedicalClient;
import com.amazonaws.services.comprehendmedical.model.DetectEntitiesV2Request;
import com.amazonaws.services.comprehendmedical.model.DetectEntitiesV2Result;
import com.amazonaws.services.comprehendmedical.model.Entity;
import com.dokyoulee.example.awscomprehendmedical.config.AwsProperties;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/prescription")
public class PrescriptionController {

    @Resource
    AwsProperties awsProperties;

    @ResponseBody
    @GetMapping(value = "/parse")
    public List<Entity> parsePrescription(@RequestParam String text) {
        AWSCredentials credentials = new BasicAWSCredentials(
                awsProperties.getAccessKey(),
                awsProperties.getSecretKey());

        AWSCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(credentials);

        System.out.println(awsProperties.getAccessKey());
        System.out.println(awsProperties.getSecretKey());

        AWSComprehendMedical client = AWSComprehendMedicalClient.builder()
                .withCredentials(credentialsProvider)
                .withRegion("us-west-2")
                .build();

        DetectEntitiesV2Request request = new DetectEntitiesV2Request();
        request.setText(text);

        DetectEntitiesV2Result result = client.detectEntitiesV2(request);
        return result.getEntities();
    }
}