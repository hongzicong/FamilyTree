package hongzicong.familytree;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class EditData extends AppCompatActivity {

    private ImageView headerPicture;
    private EditText editName;
    private EditText editAge;
    private Button sureButton;
    private Toolbar mToolbar;

    private String name;
    private int picture;
    private int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_edit_data);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        initWidget();
        setOnAllListener();
        //todo
        // getDataFromIntent();

    }

    private void getDataFromIntent(){
        Intent intent=getIntent();
        name=intent.getStringExtra("member_name");
        picture=intent.getIntExtra("member_picture",-1);
        age=intent.getIntExtra("member_age",-1);
    }

    private void initWidget(){
        sureButton=(Button)findViewById(R.id.sure_edit);
        editAge=(EditText)findViewById(R.id.edit_age);
        editName=(EditText)findViewById(R.id.edit_name);
        headerPicture=(ImageView)findViewById(R.id.edit_picture);
        mToolbar=(Toolbar)findViewById(R.id.edit_layout_toolbar);
        setSupportActionBar(mToolbar);
    }

    private void setOnAllListener(){
        sureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("member_name_return",name);
                intent.putExtra("member_age_return",age);
                intent.putExtra("member_picture_return",picture);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

}
