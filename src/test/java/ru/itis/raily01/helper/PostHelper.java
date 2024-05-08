package ru.itis.raily01.helper;

import org.openqa.selenium.By;
import ru.itis.raily01.ApplicationManager;
import ru.itis.raily01.model.PostData;

public class PostHelper extends HelperBase {
    public void createPost(PostData post) throws InterruptedException {
        manager.js.executeScript("window.scrollBy(0,450)", "");
        Thread.sleep(2000);
        driver.findElement(By.id("comments")).click();
        driver.findElement(By.id("comments")).sendKeys(post.content);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"buttons\"]/div[2]/input")).click();
//        driver.findElement(By.xpath("input[contains(@value, 'Добавить'")).click();
    }

    public PostData getLastUserPost() {
        var lastPostElem = driver
                .findElement(By.className("ReviewsList"))
                .findElement(By.className("Review"));
        String postText = lastPostElem
                .findElement(By.className("textw"))
                .findElement(By.className("maintext")).getText();
        return new PostData(postText);
    }

    public String getLastPostId() {
        var lastPostElem = driver
                .findElement(By.className("ReviewsList"))
                .findElement(By.className("Review"));
        return lastPostElem.getAttribute("id");
    }

    public void editPostById(String id, String newContent) throws InterruptedException {
        var postElem = driver.findElement(By.id(id));
        postElem.findElement(By.className("wall_icon_img_edit")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("editform")).click();
        driver.findElement(By.id("editform")).clear();
        driver.findElement(By.id("editform")).sendKeys(newContent);
        driver.findElement(By.xpath("/html/body/div[9]/div[3]/div/button[2]/span")).click();
    }

    public PostData getPostById(String id) {
        var postText = driver.findElement(By.id(id))
                .findElement(By.className("textw"))
                .findElement(By.className("maintext")).getText();
        return new PostData(postText);
    }

    public PostHelper(ApplicationManager manager) {
        super(manager);
    }

}
