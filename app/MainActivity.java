import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MessageAdapter messageAdapter;
    private List<Message> messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        messageList = new ArrayList<>();
        messageAdapter = new MessageAdapter(messageList);
        recyclerView.setAdapter(messageAdapter);

        Button sendButton = findViewById(R.id.send_button);
        
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText messageEditText = findViewById(R.id.message_edit_text);
                String messageText = messageEditText.getText().toString();

                sendMessage(messageText);
                messageEditText.setText("");
            }
        });

        receiveMessages();
    }

    private void sendMessage(String messageText) {
        // Отправка сообщения на сервер. В данном примере мы будем считать, что отправка происходит успешно,поэтому добавим отправленное сообщение в список и обновим адаптер
               
        Message sentMessage = new Message(messageText);
        messageList.add(sentMessage);
        messageAdapter.notifyDataSetChanged();
    }

    private void receiveMessages() {
        // Получение новых сообщений с сервера
        // В данном примере мы создаем несколько тестовых сообщений,
        // добавляем их в список и обновляем адаптер

        Message receivedMessage1 = new Message("Привет!");
        Message receivedMessage2 = new Message("Как дела?");
        Message receivedMessage3 = new Message("все отлично!");

        messageList.add(receivedMessage1);
        messageList.add(receivedMessage2);
        messageList.add(receivedMessage3);

        messageAdapter.notifyDataSetChanged();
    }
}


