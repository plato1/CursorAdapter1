package com.abnanodegree.jk.cursoradapter1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import static com.abnanodegree.jk.cursoradapter1.R.id.lv_Items;
import static com.abnanodegree.jk.cursoradapter1.TodoItemDatabase.ROW_ID;
import static com.abnanodegree.jk.cursoradapter1.TodoItemDatabase.ROW_NAME;
import static com.abnanodegree.jk.cursoradapter1.TodoItemDatabase.TABLE_NAME;

/**
 * https://github.com/codepath/android_guides/wiki/Populating-a-ListView-with-a-CursorAdapter
 * https://github.com/codepath/android_guides/wiki/Persisting-Data-to-the-Device#sqlite
 * https://developer.android.com/reference/android/database/sqlite/SQLiteOpenHelper.html
 *
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TodoDatabaseHandler is a SQLiteOpenHelper class connecting to SQLite
        TodoItemDatabase handler = new TodoItemDatabase(this);
        // Get access to the underlying writeable database
        SQLiteDatabase db = handler.getWritableDatabase();

        // create table if not exists
        String Table = "create table if not exists " + TABLE_NAME + " ( " + ROW_ID + " integer primary key, "
                + ROW_NAME + " text not null" + " ) ";
        db.execSQL(Table);

        // delete all prior rows from table
        db.execSQL("DELETE FROM "+ TABLE_NAME);

        // Insert some rows
        ContentValues values = new ContentValues();
        values.put(ROW_ID, 1);
        values.put(ROW_NAME, "name1");
        db.insert(TABLE_NAME, null, values);

        values.put(ROW_ID, 2);
        values.put(ROW_NAME, "name2");
        db.insert(TABLE_NAME, null, values);


        // Query for items from the database and get a cursor back
        Cursor todoCursor = db.rawQuery("SELECT  * FROM "+ TABLE_NAME, null);

        Log.d("TAG", DatabaseUtils.dumpCursorToString(todoCursor));

        // Alt 1 - send items to Textview   ------------

        TextView view = (TextView) findViewById(R.id.hello);
        view.append("\n Count of items - " + todoCursor.getCount());
        todoCursor.moveToFirst();
        while (todoCursor.isAfterLast() == false) {
            view.append("\n" + todoCursor.getInt(0) + "  " + todoCursor.getString(1));
            todoCursor.moveToNext();
        }
        todoCursor.close();

        //Alt 2 - use ListView -------------------------

        // Query for items from the database and get a cursor back
        todoCursor = db.rawQuery("SELECT  * FROM "+ TABLE_NAME, null);
        // Find ListView to populate
        ListView lvItems = (ListView) findViewById(lv_Items);
        // Setup cursor adapter using cursor from last step
        TodoCursorAdapter todoAdapter = new TodoCursorAdapter(this, todoCursor);
        // Attach cursor adapter to the ListView
        lvItems.setAdapter(todoAdapter);
        todoCursor.close();

        // Switch to new cursor and update contents of ListView
       // todoAdapter.changeCursor(newCursor);
    }
}
