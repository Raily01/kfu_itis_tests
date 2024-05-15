package ru.itis.raily01.test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import ru.itis.raily01.ApplicationManager;
import ru.itis.raily01.TestDataGenerator;
import ru.itis.raily01.base.AuthBase;
import ru.itis.raily01.model.PostData;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Theories.class)
public class CreatePostTest extends AuthBase {

    @DataPoint
    public static List<PostData> getGeneratedPosts() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Path testDataPath = Path.of("generated.json");
        if (!Files.exists(testDataPath)) {
            TestDataGenerator.generate(testDataPath, 1, 100);
        }

        List<PostData> posts = null;
        try (var is = Files.newInputStream(testDataPath, StandardOpenOption.READ)) {
            posts = objectMapper.readValue(is.readAllBytes(), new TypeReference<List<PostData>>() {});
        }
        return posts;
    }

    @Theory
    public void createPost(List<PostData> postData) throws InterruptedException {
        // Получаем экземпляр ru.itis.raily01.ApplicationManager
        ApplicationManager manager = ApplicationManager.getInstance();
        manager.goTo().openAccountPage();
        for (var post: postData) {
            manager.post().createPost(post);
            Thread.sleep(5000);
            PostData lastUserPost = manager.post().getLastUserPost();
            assertEquals(post.getContent(), lastUserPost.getContent().replaceAll("\n", ""));
        }
    }
}
