package bibliotech_bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class BiblioTech extends TelegramLongPollingBot {
	public String getBotUsername() {
		return "BiblioTech";
	}
	@Override
	public String getBotToken() {
	    // token del bot
		return "1302566731:AAGU54BY4Q0xxJ494c7A8eoqpCOzQeemKAU";
	}
	@SuppressWarnings("null")
	public void onUpdateReceived(Update update) {
		
        String msg = update.getMessage().getText();
        long chatId=update.getMessage().getChatId();
        
        String command = "/utente ";
        
        // comando /start : benvenuto al utente
        if (msg.equals("/start")) {
           
        	SendMessage welcome = new SendMessage();
            welcome.setChatId(chatId);
            welcome.setText("Benvenuto nel bot di BiblioTech");
            
            try {
            	execute(welcome);
            } catch (TelegramApiException e) {
            	e.printStackTrace();
            }
        }
        
     // comando /utente : l'utente inserisce il proprio Codice Utente
        else if (msg.startsWith(command)) {
	       
        	String cf = msg.substring(command.length());
        	long user_idL = update.getMessage().getChat().getId();
        	String user_id = Long.toString(user_idL);
        	
	        SendMessage ricevuto = new SendMessage();
	        ricevuto.setChatId(chatId);
	        ricevuto.setText("Codice Fiscale inserito: "+cf+"\n"+"Codice Utente: "+user_id);
	        
	        try {
	        	execute(ricevuto);

	    		System.out.println(String.format("http://2.224.243.66:8080/telegram/registrazione?id=%s&cf=%s",user_id,cf));
	    		
	    		
	    		URL url=new URL(String.format("http://2.224.243.66:8080/telegram/registrazione?id=%s&cf=%s",user_id,cf));
	    		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	    		
				connection.setRequestMethod("POST");
				
				URL url1=new URL(String.format("http://2.224.243.66:8080/telegram/utente?id=%s",user_id));
	    		HttpURLConnection connection1 = (HttpURLConnection) url1.openConnection();
	    		
				connection1.setRequestMethod("GET");
				
				System.out.println(String.format("http://2.224.243.66:8080/telegram/registrazione?id=%s",user_id));
	        
				BufferedReader read = new BufferedReader(new InputStreamReader(connection1.getInputStream()));
				
				String inputLine;
			    StringBuffer response = new StringBuffer();
			    
			    while ((inputLine = read.readLine()) != null) {
			     	response.append(inputLine);
			    }
			  
			    JSONObject obj = new JSONObject(response.toString());

			    Calendar today = Calendar.getInstance();
		        today.set(Calendar.DAY_OF_MONTH, 0);
		        Date dataToday=today.getTime();
			    
			    
			    SendMessage datiLibro = new SendMessage();
		        datiLibro.setChatId(chatId);
		        
		        SendMessage elencoL = new SendMessage();
		        elencoL.setChatId(chatId);
		        elencoL.setText("Ecco i tuoi libri in prestito:"+"\n");
		        
		        SendMessage scaduto = new SendMessage();
		        scaduto.setChatId(chatId);
		        
		        try {

			    	execute(elencoL);
		        }catch(TelegramApiException e) {
			    	e.printStackTrace();
			    }
			    
			    int idx=0;
			    try {
			        for (idx=0; true; idx++) {
			            JSONArray array = obj.getJSONArray("" + idx);

			            String ISBN = array.getString(0);
			            String data_prestito = array.getString(1);
			            
			            Calendar cal = Calendar.getInstance();
			            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			            cal.setTime(sdf.parse(data_prestito));
			            cal.add(Calendar.DATE, 30);
			            Date dataScadenza=cal.getTime();
					   
					    
					    System.out.println("dprestito: "+data_prestito+"\n"+"dataScadenza: "+dataScadenza.toString());
			            
			            datiLibro.setText("ISBN: "+ISBN+"   "+"Data Prestito: "+data_prestito+"\n\n");
			            
					    try {
					    	execute(datiLibro);
					    	if(dataScadenza.compareTo(dataToday) <= 0) {	//per mandare messaggio per prestito scaduto

						        scaduto.setText("PRESTITO  "+ISBN+ "  SCADUTO, RIPORTARE IN BIBLIOTECA"+"\n");
						        execute(scaduto);
					    	}
					    	
					    }catch(TelegramApiException e) {
					    	e.printStackTrace();
					    }
					    
			        }
			      
			    } catch (JSONException ex) {
			        System.out.println("Nell'oggetto ci sono " + idx + " valori");
			    }
			  
	        }catch(TelegramApiException e) {
	        	e.printStackTrace();
	        }catch(IOException e) {
	    		System.out.println("DB Error");
	    		e.printStackTrace();
	    	}catch(Exception e) {
	    		System.out.println("Libro non trovato!");
	    		e.printStackTrace();
	    	}
	        
	        
	      //ciclo di Update informazioni prestiti al Utente
	        
	 	       while(true) {
	 	    	   try {
	 	    		   
					Thread.sleep(100000); // ogni quanto deve mandare un messaggio all'utente
					
					URL url1=new URL(String.format("http://2.224.243.66:8080/telegram/utente?id=%s",user_id));
		    		HttpURLConnection connection1 = (HttpURLConnection) url1.openConnection();
		    		
					connection1.setRequestMethod("GET");
					
					System.out.println(String.format("http://2.224.243.66:8080/telegram/registrazione?id=%s",user_id));
		        
					BufferedReader read = new BufferedReader(new InputStreamReader(connection1.getInputStream()));
					
					String inputLine;
				    StringBuffer response = new StringBuffer();
				    
				    while ((inputLine = read.readLine()) != null) {
				     	response.append(inputLine);
				    }
				  
				    JSONObject obj = new JSONObject(response.toString());

				    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				    Date dataToday=java.util.Calendar.getInstance().getTime();
				    
				    
				    SendMessage datiLibro = new SendMessage();
			        datiLibro.setChatId(chatId);
			        
			        SendMessage elencoL = new SendMessage();
			        elencoL.setChatId(chatId);
			        elencoL.setText("Ecco i tuoi libri in prestito:"+"\n");
			        
			        SendMessage scaduto = new SendMessage();
			        scaduto.setChatId(chatId);
			        
			        try {

				    	execute(elencoL);
			        }catch(TelegramApiException e) {
				    	e.printStackTrace();
				    }
				    
				    int idx=0;
				    try {
				        for (idx=0; true; idx++) {
				            JSONArray array = obj.getJSONArray("" + idx);

				            String ISBN = array.getString(0);
				            String data_prestito = array.getString(1);
				            
				            Calendar cal = Calendar.getInstance();
				            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				            cal.setTime(sdf.parse(data_prestito));
				            cal.add(Calendar.DATE, 30);
				            Date dataScadenza=cal.getTime();
						   
						    
						    System.out.println("dprestito: "+data_prestito+"\n"+"dataScadenza: "+dataScadenza.toString());
				            
				            datiLibro.setText("ISBN: "+ISBN+"   "+"Data Prestito: "+data_prestito+"\n\n");
						    try {
						    	execute(datiLibro);
						    	if(dataScadenza.compareTo(dataToday) <= 0) {	//per mandare messaggio per prestito scaduto

							        scaduto.setText("PRESTITO  "+ISBN+ "  SCADUTO, RIPORTARE IN BIBLIOTECA"+"\n");
							        execute(scaduto);
						    	}
						    	
						    }catch(TelegramApiException e) {
						    	e.printStackTrace();
						    }
						    
				        }
				      
				    } catch (JSONException ex) {
				        System.out.println("Nell'oggetto ci sono " + idx + " valori");
				    }
					
					
				} catch (InterruptedException e) {
					e.printStackTrace();
		        }catch(IOException e) {
		    		System.out.println("DB Error");
		    		e.printStackTrace();
		    	}catch(Exception e) {
		    		System.out.println("Libro non trovato!");
		    		e.printStackTrace();
		    	}
	 	    	   
	 	       }
	 	      
	 	       
        }
        else if(msg.equals("/help")){
        	
        	SendMessage help = new SendMessage();
            help.setChatId(chatId);
            help.setText("Per iniziare, invia il seguente comando:\r\n"
            		+ "\r\n"
            		+ "/utente Codice_Fiscale\r\n"
            		+ "\r\n"
            		+ "Dove al posto di Codice_Fiscale inserisci il proprio Codice Fiscale.");
            
            try {
            	execute(help);
            } catch (TelegramApiException e) {
            	e.printStackTrace();
            }
        }
        else {
            // Comando Sconosciuto
            SendMessage message = new SendMessage()
                            .setChatId(chatId)
                            .setText("Comando Sconosciuto");
            try {
                execute(message);
	    		
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        
        
        if(update.getMessage().hasText()) {
		 	//log
	        String user_first_name = update.getMessage().getChat().getFirstName();
	        String user_last_name = update.getMessage().getChat().getLastName();
	        String user_username = update.getMessage().getChat().getUserName();
	        long user_id = update.getMessage().getChat().getId();
	        String message_text = update.getMessage().getText();
	        log(user_first_name, user_last_name,user_username, Long.toString(user_id), message_text);
        }   
	       
        
    }
	
	private void log(String first_name, String last_name,String user_name, String user_id, String txt) {
        System.out.println("\n ----------------------------");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        System.out.println("Messaggio da " + user_name +" "+first_name + " " + last_name + " (id = " + user_id + ") \n Messaggio:  " + txt);
    }
}