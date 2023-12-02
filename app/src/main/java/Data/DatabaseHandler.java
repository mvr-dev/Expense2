package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import Model.Contribution;
import Utils.Util;

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DATABASE ="CREATE TABLE "+ Util.TABLE_NAME +
                " ("+Util.KEY_ID+" INTEGER PRIMARY KEY, "+
                Util.KEY_TYPE+" INTEGER NOT NULL, "+
                Util.KEY_GOAL+" TEXT, "+
                Util.KEY_VALUE+" INTEGER NOT NULL)";
        db.execSQL(CREATE_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ Util.TABLE_NAME);
        onCreate(db);
    }
    public void addCont(Contribution contribution){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_TYPE,contribution.getType());
        contentValues.put(Util.KEY_GOAL,contribution.getGoal());
        contentValues.put(Util.KEY_VALUE,contribution.getValue());
        db.insert(Util.TABLE_NAME,null,contentValues);
        db.close();
    }
    public Contribution getCont(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Util.TABLE_NAME,
                new String[]{Util.KEY_ID,Util.KEY_TYPE,Util.KEY_GOAL,Util.KEY_VALUE},
                Util.KEY_ID+"=?",
                new String[]{String.valueOf(id)},null,null,null,null);
        if (cursor!=null){
            cursor.moveToFirst();
        }
        Contribution contribution = new Contribution(Integer.parseInt(cursor.getString(0)),
                Integer.parseInt(cursor.getString(1)),
                cursor.getString(2),
                Integer.parseInt(cursor.getString(3)));
        db.close();
        return contribution;
    }
    public List<Contribution> getAllCont(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Contribution> contributions = new ArrayList<>();
        String SELECT_ALL = "SELECT * FROM "+Util.TABLE_NAME;
        Cursor cursor =db.rawQuery(SELECT_ALL,null);
        if (cursor.moveToFirst()){
            do{
                Contribution contribution = new Contribution();
                contribution.setId(Integer.parseInt(cursor.getString(0)));
                contribution.setType(Integer.parseInt(cursor.getString(1)));
                contribution.setGoal(cursor.getString(2));
                contribution.setValue(Integer.parseInt(cursor.getString(3)));
                contributions.add(contribution);
            }while (cursor.moveToNext());
        }
        return contributions;
    }
    public int updateCont(Contribution contribution){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_TYPE,contribution.getType());
        contentValues.put(Util.KEY_GOAL,contribution.getGoal());
        contentValues.put(Util.KEY_VALUE,contribution.getValue());
        return db.update(Util.TABLE_NAME,contentValues,Util.KEY_ID+"=?",
                new String[]{String.valueOf(contribution.getId())});
    }
    public void deleteCont(Contribution contribution){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Util.TABLE_NAME,Util.KEY_ID+"=?",
                new String[]{String.valueOf(contribution.getId())});
        db.close();
    }


}

