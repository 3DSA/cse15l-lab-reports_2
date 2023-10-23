import java.io.IOException;
import java.net.URI;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    int num = 0;
    private static ArrayList<String> messages = new ArrayList<>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            StringBuilder response = new StringBuilder();
            for (int i = 0; i < messages.size(); i++) {
                response.append((i + 1) + ". " + messages.get(i) + "\n");
            }
            return response.toString();
        } 
        else {
            if (url.getPath().contains("/add-message?s")) {
                String parameters = url.getQuery().split("=");
                messages.add(parameters);
                StringBuilder response = new StringBuilder();
                for (int i = 0; i < messages.size(); i++) {
                    response.append((i + 1) + ". " + messages.get(i) + "\n");
                }
                return response.toString();   
               
            }
            return "404 Not Found!";
        }
    }
}

class StringServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
