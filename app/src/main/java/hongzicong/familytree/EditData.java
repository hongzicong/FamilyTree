package hongzicong.familytree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class EditData extends AppCompatActivity {

    private ImageView headerPicture;
    private EditText editName;
    private EditText editAge;
    private Button sureEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        initWidget();
    }

    private void initWidget(){
        sureEdit=(Button)findViewById(R.id.sure_edit);
        editAge=(EditText)findViewById(R.id.edit_age);
        editName=(EditText)findViewById(R.id.edit_name);
        headerPicture=(ImageView)findViewById(R.id.edit_picture);
    }
}
