package io.github.cursodsousa.isales.invoicing.bucket;

import io.github.cursodsousa.isales.invoicing.config.props.MinioProps;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.Http;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class BucketService {
    private final MinioClient minioClient;
    private  final MinioProps minioProps;

    public void upload(BucketFile bucketFile) {
        try {
            var object  = PutObjectArgs
                    .builder()
                    .bucket(minioProps.getBucketName())
                    .object(bucketFile.name())
                    .stream(bucketFile.stream(), bucketFile.size(), Long.valueOf(-1))
                    .contentType(bucketFile.type().toString())
                    .build();
            minioClient.putObject(object);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public String getUrl(String fileName) {
        try {
            var object = GetPresignedObjectUrlArgs.builder()
                    .method(Http.Method.GET)
                    .bucket(minioProps.getBucketName())
                    .object(fileName)
                    .expiry(1, TimeUnit.HOURS)
                    .build();

            return minioClient.getPresignedObjectUrl(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
