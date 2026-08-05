package io.github.cursodsousa.isales.invoicing.bucket;

import org.springframework.http.MediaType;

import java.io.InputStream;

public record BucketFile(String name, InputStream stream, MediaType type, long size) {

}
