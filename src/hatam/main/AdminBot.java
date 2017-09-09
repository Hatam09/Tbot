package hatam.main;

import java.util.Comparator;
import java.util.List;

import org.telegram.telegrambots.api.methods.send.SendDocument;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.methods.send.SendVideo;
import org.telegram.telegrambots.api.methods.send.SendVoice;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.PhotoSize;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import functions.CaptionChanger;
import functions.SendPost;

public class AdminBot extends TelegramLongPollingBot {

	private String channel_id = "-1001108091091";
	private String channel_username = "@test";

	@Override
	public String getBotUsername() {
		// TODO Auto-generated method stub
		return "cadmin1994_bot";
	}

	@Override
	public String getBotToken() {
		// TODO Auto-generated method stub
		return "421361318:AAEYq_1zUJ2XgKWtAkEAy0BGNDHNYPR2dWY";
	}

	@Override
	public void onUpdateReceived(Update update) {

		// check if it is a reply
		if (update.getMessage().isReply()) {
			System.out.println("its a reply");
			// if it is a send command
			if (update.getMessage().getText().equalsIgnoreCase("send")) {
				System.out.println("its a send");

				// ----------------------------------------------------------------------------
				// We check if the update has a message and the message has text
				if (update.hasMessage()
						&& update.getMessage().getReplyToMessage().hasText()) {
					System.out.println("its a text: \n" + update);
					System.out.println("------------------------------------");
					// Set variables
					String message_text = update.getMessage()
							.getReplyToMessage().getText();

					SendMessage test = new SendMessage(channel_id, message_text);

					try {
						sendMessage(test); // Sending our message object
											// to user
					} catch (TelegramApiException e) {
						e.printStackTrace();
					}
				}

				// ----------------------------------------------------------------------------
				// if it is photo
				else if (update.hasMessage()
						&& update.getMessage().getReplyToMessage().hasPhoto()) {
					System.out.println("its a photo: \n" + update);
					System.out.println("------------------------------------");

					// Message contains photo
					// Set variables

					List<PhotoSize> photos = update.getMessage()
							.getReplyToMessage().getPhoto();
					// Know file_id
					String f_id = photos
							.stream()
							.sorted(Comparator
									.comparing(PhotoSize::getFileSize)
									.reversed()).findFirst().orElse(null)
							.getFileId();

					String caption = "";
					if (update.getMessage().getCaption() != null) {

						caption = update.getMessage().getReplyToMessage()
								.getCaption().toString();
					}
					SendPhoto msg = new SendPhoto().setChatId(channel_id)
							.setPhoto(f_id).setCaption(caption);
					try {
						sendPhoto(msg); // Call method to send the photo with
										// caption
					} catch (TelegramApiException e) {
						e.printStackTrace();
					}

				}

				// ----------------------------------------------------------------------------
				// if it is doc
				else if (update.hasMessage()
						&& update.getMessage().getReplyToMessage()
								.hasDocument()) {
					System.out.println("its a document: \n" + update);
					System.out.println("------------------------------------");

					// Set variables
					String caption = "";
					if (update.getMessage().getCaption() != null) {

						caption = update.getMessage().getReplyToMessage()
								.getCaption().toString();
					}
					SendDocument msg = new SendDocument()
							.setChatId(channel_id)
							.setDocument(
									update.getMessage().getReplyToMessage()
											.getDocument().getFileId())
							.setCaption(caption);

					try {
						sendDocument(msg); // Call method to send the photo with
											// caption
					} catch (TelegramApiException e) {
						e.printStackTrace();
					}

				}

				// ----------------------------------------------------------------------------
				// if it is video
				else if (update.hasMessage()
						&& update.getMessage().getReplyToMessage().getVideo() != null) {
					System.out.println("its a video: \n" + update);
					System.out.println("------------------------------------");

					// Set variables
					String caption = "";
					if (update.getMessage().getCaption() != null) {
						caption = update.getMessage().getReplyToMessage()
								.getCaption().toString();
					}
					SendVideo msg = new SendVideo()
							.setChatId(channel_id)
							.setVideo(
									update.getMessage().getReplyToMessage()
											.getVideo().getFileId())
							.setCaption(caption);

					try {
						sendVideo(msg); // Call method to send the photo with
										// caption
					} catch (TelegramApiException e) {
						e.printStackTrace();
					}
				}

				// ----------------------------------------------------------------------------
				// if it is voice
				else if (update.hasMessage()
						&& update.getMessage().getReplyToMessage().getVoice() != null) {
					System.out.println("its a voice: \n" + update);
					System.out.println("------------------------------------");

					// Set variables

					String caption = "";
					if (update.getMessage().getCaption() != null) {
						caption = update.getMessage().getReplyToMessage()
								.getCaption().toString();
					}
					SendVoice msg = new SendVoice()
							.setChatId(channel_id)
							.setVoice(
									update.getMessage().getReplyToMessage()
											.getVoice().getFileId())
							.setCaption(caption);

					try {
						sendVoice(msg); // Call method to send the photo with
										// caption
					} catch (TelegramApiException e) {
						e.printStackTrace();
					}
				}

				// ----------------------------------------------------------------------------

			}

			return;
		}
		// ****************************************************************************
		// ----------------------------------------------------------------------------
		// We check if the update has a message and the message has text
		else if (update.hasMessage() && update.getMessage().hasText()) {
			System.out.println("its a text: \n" + update);
			System.out.println("------------------------------------");

			// Set variables
			String message_text = update.getMessage().getText();
			long chat_id = update.getMessage().getChatId();
			message_text = CaptionChanger.captionchanger(message_text);
			SendMessage test = new SendMessage(chat_id, message_text);
			try {
				sendMessage(test); // Sending our message object
									// to user
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		}

		// ----------------------------------------------------------------------------
		// if it is photo
		else if (update.hasMessage() && update.getMessage().hasPhoto()) {
			System.out.println("its a photo: \n" + update);
			System.out.println("------------------------------------");

			// Message contains photo
			// Set variables
			long chat_id = update.getMessage().getChatId();

			// Array with photo objects with different sizes
			// We will get the biggest photo from that array
			List<PhotoSize> photos = update.getMessage().getPhoto();
			// Know file_id
			String f_id = photos
					.stream()
					.sorted(Comparator.comparing(PhotoSize::getFileSize)
							.reversed()).findFirst().orElse(null).getFileId();

			String caption = "\n" + channel_username;
			if (update.getMessage().getCaption() != null) {
				caption = CaptionChanger.captionchanger(update.getMessage()
						.getCaption().toString());
			}

			SendPhoto msg = new SendPhoto().setChatId(chat_id).setPhoto(f_id)
					.setCaption(caption);
			try {
				sendPhoto(msg); // Call method to send the photo with caption
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}

		}

		// ----------------------------------------------------------------------------
		// if it is doc
		else if (update.hasMessage() && update.getMessage().hasDocument()) {
			System.out.println("its a document: \n" + update);
			System.out.println("------------------------------------");

			// Set variables
			long chat_id = update.getMessage().getChatId();
			String caption = "\n" + channel_username;
			System.out.println(update.getMessage().getCaption());
			if (update.getMessage().getCaption() != null) {
				caption = CaptionChanger.captionchanger(update.getMessage()
						.getCaption().toString());
			}
			SendDocument msg = new SendDocument().setChatId(chat_id)
					.setDocument(update.getMessage().getDocument().getFileId())
					.setCaption(caption);

			try {
				sendDocument(msg); // Call method to send the photo with caption
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}

		}

		// ----------------------------------------------------------------------------
		// if it is video
		else if (update.hasMessage() && update.getMessage().getVideo() != null) {
			System.out.println("its a video: \n" + update);
			System.out.println("------------------------------------");

			// Set variables
			long chat_id = update.getMessage().getChatId();
			String caption = "\n" + channel_username;
			if (update.getMessage().getCaption() != null) {
				caption = CaptionChanger.captionchanger(update.getMessage()
						.getCaption().toString());
			}
			SendVideo msg = new SendVideo().setChatId(chat_id)
					.setVideo(update.getMessage().getVideo().getFileId())
					.setCaption(caption);

			try {
				sendVideo(msg); // Call method to send the photo with caption
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		}

		// ----------------------------------------------------------------------------
		// if it is voice
		else if (update.hasMessage() && update.getMessage().getVoice() != null) {
			System.out.println("its a voice: \n" + update);
			System.out.println("------------------------------------");

			// Set variables
			long chat_id = update.getMessage().getChatId();
			String caption = "\n" + channel_username;
			if (update.getMessage().getCaption() != null) {
				caption = CaptionChanger.captionchanger(update.getMessage()
						.getCaption().toString());
			}
			SendVoice msg = new SendVoice().setChatId(chat_id)
					.setVoice(update.getMessage().getVoice().getFileId())
					.setCaption(caption);

			try {
				sendVoice(msg); // Call method to send the photo with caption
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		}

		// ----------------------------------------------------------------------------
	}
}
