package ru.itis.raily01.test;

import org.junit.Test;
import ru.itis.raily01.ApplicationManager;
import ru.itis.raily01.model.AccountData;
import ru.itis.raily01.model.PostData;

import static org.junit.Assert.assertEquals;

public class EditPostTest extends TestBase {

    @Test
    public void editPost() throws InterruptedException {
        ApplicationManager manager = ApplicationManager.getInstance();

        manager.goTo().OpenHomePage();
        AccountData user = new AccountData("Railyy", "12345678");
        manager.login().Login(user);

        manager.goTo().openAccountPage();
        PostData postData = new PostData("Some content for the new post");
        manager.post().createPost(postData);

        String lastPostId = manager.post().getLastPostId();

        String newContent = "Редактирование поста.";
        manager.post().editPostById(lastPostId, newContent);
        Thread.sleep(5000);
        PostData editedPost = manager.post().getPostById(lastPostId);

        assertEquals(newContent, editedPost.getContent());
    }
}
