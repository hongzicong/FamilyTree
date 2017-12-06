package hongzicong.familytree;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class EditData extends AppCompatActivity {

    private ImageView headerPicture;
    private EditText editName;
    private EditText editAge;
    private Button sureEdit;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_edit_data);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }

        initWidget();
    }

    private void initWidget(){
        sureEdit=(Button)findViewById(R.id.sure_edit);
        editAge=(EditText)findViewById(R.id.edit_age);
        editName=(EditText)findViewById(R.id.edit_name);
        headerPicture=(ImageView)findViewById(R.id.edit_picture);
        mToolbar=(Toolbar)findViewById(R.id.edit_layout_toolbar);
        setSupportActionBar(mToolbar);
    }
}
