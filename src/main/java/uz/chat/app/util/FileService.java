package uz.chat.app.util;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.response.GetFileResponse;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

@Slf4j
@Component
public class FileService {

    @Value("${telegram.botToken}")
    private String botToken;

    @Value("${telegram.channelId}")
    private String channelId;

    // We can use a private Telegram channel as a server to store user avatars
    // (but this method is not good for real projects, because our project will be tied to Telegram)

    public String uploadFileToTelegramChannel(MultipartFile file) throws IOException {
        TelegramBot telegramBot = new TelegramBot(botToken);

        SendResponse response = telegramBot.execute(new SendPhoto(channelId, file.getBytes()));

        PhotoSize[] photos = response.message().photo();
        GetFile request = new GetFile(photos[photos.length-1].fileId());
        GetFileResponse getFileResponse = telegramBot.execute(request);

        return telegramBot.getFullFilePath(getFileResponse.file());
    }



}
