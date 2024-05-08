package ru.itis.raily01;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.stream.IntStreams;
import ru.itis.raily01.model.PostData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Objects;

public class TestDataGenerator {

    public static void main(String[] args) throws Exception {
        var postCount = Integer.valueOf(Objects.requireNonNull(args[0], "Параметр количества постов не может быть пустым"));
        var postLength = Integer.valueOf(Objects.requireNonNull(args[1], "Параметр  не может быть пустым"));
        generate(Path.of("C:\\Users\\shayk\\IdeaProjects\\test\\generated.json"), postCount, postLength);
    }

    public static void generate(Path path, int postCount, int postLength) throws Exception {
        var objectMapper = new ObjectMapper();
        List<PostData> generatedPosts = IntStreams.range(postCount)
                .mapToObj(i -> {
                    return new PostData(RandomStringUtils.randomAlphabetic(postLength));
                })
                .toList();
        Files.deleteIfExists(path);
        try (var os = Files.newOutputStream(path, StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE)) {
            os.write(objectMapper.writeValueAsBytes(generatedPosts));
        }
        RandomStringUtils.randomAlphabetic(postLength);
    }
}
