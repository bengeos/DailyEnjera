package bengeos.com.dailynews.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by bengeos on 1/16/17.
 */

public class Database {
    public static final String TAG = "mySqLiteDatabase";


    public static final String Table_Albums = "Tenders";
    public static final String Table_Favourite = "Favourite";
    public static final String Table_LiveChannels = "LiveChannels";

    private Context myContext;
    private SQLHelper mySQL;
    private SQLiteDatabase mySqLiteDatabase;

    public enum AlbumsTable {
        ID("id"),
        SERID("SerID"),
        Title("Title"),
        ImageURL("ImageURL"),
        VideoURL("VideoURL"),
        Channel("Channel"),
        Category("Category"),
        PubDate("PubDate");
        private final String name;
        AlbumsTable(String s) { this.name = s; }
        public boolean equalsName(String otherName) { return (otherName == null) ? false : name.equals(otherName); }
        @Override public String toString() { return this.name; }
    }
    public enum LiveChannalesTable {
        ID("id"),
        SERID("SerID"),
        Name("Name"),
        StreamURL("VideoURL");
        private final String name;
        LiveChannalesTable(String s) { this.name = s; }
        public boolean equalsName(String otherName) { return (otherName == null) ? false : name.equals(otherName); }
        @Override public String toString() { return this.name; }
    }
    public static <E extends Enum<E>> String[] getStrArrFromEnum(Class<E> e) {
        Enum<E>[] enumConstants = e.getEnumConstants();
        int numConstants = enumConstants.length;
        String[] strArr = new String[numConstants];
        for (int i = 0; i < numConstants; i++) {
            strArr[i] = enumConstants[i].toString();
        }
        return  strArr;
    }

    public Database(Context context) {
        myContext = context;
        mySQL = new SQLHelper(myContext);
        mySqLiteDatabase = mySQL.getWritableDatabase();
        mySQL.createTable(Table_Albums, getStrArrFromEnum(AlbumsTable.class));
        mySQL.createTable(Table_Favourite, getStrArrFromEnum(AlbumsTable.class));
        mySQL.createTable(Table_LiveChannels, getStrArrFromEnum(LiveChannalesTable.class));

    }
    public long addNew(String DB_Table, ContentValues cv) {
        long state = mySqLiteDatabase.insert(DB_Table, null, cv);
        Log.i(TAG, "Inserting New Item->: " + cv.toString());
        return state;
    }

    public long deleteAll(String DB_Table) {
        long state = mySqLiteDatabase.delete(DB_Table, null, null);
        return state;
    }

    public long deleteByID(String DB_Table, int id) {
        String[] args = {"" + id};
        long val = mySqLiteDatabase.delete(DB_Table, "id = ?", args);
        return val;
    }

    public long updateByID(String DB_Table, ContentValues cv, int id) {
        String[] args = {"" + id};
        long state = mySqLiteDatabase.update(DB_Table, cv, "id = ?", args);
        return state;
    }

    public int countRows(String DB_Table) {
        Cursor c = mySqLiteDatabase.query(DB_Table, getColumns(DB_Table), null, null, null, null, null);
        if (c != null) {
            return c.getCount();
        } else {
            return 0;
        }
    }
    ///#####################################################################################################
    ///##########################################     Albums    ###########################################
    ///#####################################################################################################
//    public Album getAlbumByID(int id){
//        String DB_Table = Table_Albums;
//        try {
//            Cursor c = mySqLiteDatabase.query(DB_Table, getColumns(DB_Table), null, null, null, null, null);
//            if (c.getCount() > 0) {
//                c.moveToFirst();
//                for (int i = 0; i < c.getCount(); i++) {
//                    c.moveToPosition(i);
//                    int cur_id = Integer.valueOf(c.getString(c.getColumnIndex(AlbumsTable.ID.toString())));
//                    if (cur_id == id) {
//                        Album album = new Album();
//                        album.setID(Integer.valueOf(c.getString(c.getColumnIndex(AlbumsTable.ID.toString()))));
//                        album.setSerID(Integer.valueOf(c.getString(c.getColumnIndex(AlbumsTable.SERID.toString()))));
//                        album.setTitle(c.getString(c.getColumnIndex(AlbumsTable.Title.toString())));
//                        album.setCategory(c.getString(c.getColumnIndex(AlbumsTable.Category.toString())));
//                        album.setChannel(c.getString(c.getColumnIndex(AlbumsTable.Channel.toString())));
//                        album.setImageURL(c.getString(c.getColumnIndex(AlbumsTable.ImageURL.toString())));
//                        album.setVideoURL(c.getString(c.getColumnIndex(AlbumsTable.VideoURL.toString())));
//                        album.setPubDate(c.getString(c.getColumnIndex(AlbumsTable.PubDate.toString())));
//                        return album;
//                    }
//                }
//            }
//        } catch (Exception e) {
//            Log.i(TAG, "Failed getTenderByID: " + e.toString());
//            return null;
//        }
//        return null;
//    }
//    public Album getMyAlbumByID(int id){
//        String DB_Table = Table_Favourite;
//        try {
//            Cursor c = mySqLiteDatabase.query(DB_Table, getColumns(DB_Table), null, null, null, null, null);
//            if (c.getCount() > 0) {
//                c.moveToFirst();
//                for (int i = 0; i < c.getCount(); i++) {
//                    c.moveToPosition(i);
//                    int cur_id = Integer.valueOf(c.getString(c.getColumnIndex(AlbumsTable.ID.toString())));
//                    if (cur_id == id) {
//                        Album album = new Album();
//                        album.setID(Integer.valueOf(c.getString(c.getColumnIndex(AlbumsTable.ID.toString()))));
//                        album.setSerID(Integer.valueOf(c.getString(c.getColumnIndex(AlbumsTable.SERID.toString()))));
//                        album.setTitle(c.getString(c.getColumnIndex(AlbumsTable.Title.toString())));
//                        album.setCategory(c.getString(c.getColumnIndex(AlbumsTable.Category.toString())));
//                        album.setChannel(c.getString(c.getColumnIndex(AlbumsTable.Channel.toString())));
//                        album.setImageURL(c.getString(c.getColumnIndex(AlbumsTable.ImageURL.toString())));
//                        album.setVideoURL(c.getString(c.getColumnIndex(AlbumsTable.VideoURL.toString())));
//                        album.setPubDate(c.getString(c.getColumnIndex(AlbumsTable.PubDate.toString())));
//                        return album;
//                    }
//                }
//            }
//        } catch (Exception e) {
//            Log.i(TAG, "Failed getMyAlbumByID: " + e.toString());
//            return null;
//        }
//        return null;
//    }
//    public Album getAlbumBySerID(int id) {
//        String DB_Table = Table_Albums;
//        try {
//            Cursor c = mySqLiteDatabase.query(DB_Table, getColumns(DB_Table), null, null, null, null, null);
//            if (c.getCount() > 0) {
//                c.moveToFirst();
//                for (int i = 0; i < c.getCount(); i++) {
//                    c.moveToPosition(i);
//                    int ser_id = Integer.valueOf(c.getString(c.getColumnIndex(AlbumsTable.SERID.toString())));
//                    int cur_id = Integer.valueOf(c.getString(c.getColumnIndex(AlbumsTable.ID.toString())));
//                    if (ser_id == id) {
//                        Album album = getAlbumByID(cur_id);
//                        return album;
//                    }
//                }
//            }
//        } catch (Exception e) {
//            Log.i(TAG, "Failed getAlbumBySerID: " + e.toString());
//            return null;
//        }
//        return null;
//    }
//    public Album getMyAlbumBySerID(int id) {
//        String DB_Table = Table_Favourite;
//        try {
//            Cursor c = mySqLiteDatabase.query(DB_Table, getColumns(DB_Table), null, null, null, null, null);
//            if (c.getCount() > 0) {
//                c.moveToFirst();
//                for (int i = 0; i < c.getCount(); i++) {
//                    c.moveToPosition(i);
//                    int ser_id = Integer.valueOf(c.getString(c.getColumnIndex(AlbumsTable.SERID.toString())));
//                    int cur_id = Integer.valueOf(c.getString(c.getColumnIndex(AlbumsTable.ID.toString())));
//                    if (ser_id == id) {
//                        Album album = getMyAlbumByID(cur_id);
//                        return album;
//                    }
//                }
//            }
//        } catch (Exception e) {
//            Log.i(TAG, "Failed getMyAlbumBySerID: " + e.toString());
//            return null;
//        }
//        return null;
//    }
//    public Album getAlbumByChannelCategory(String channel, String category) {
//        String DB_Table = Table_Albums;
//        try {
//            Cursor c = mySqLiteDatabase.query(DB_Table, getColumns(DB_Table), null, null, null, null, null);
//            if (c.getCount() > 0) {
//                c.moveToFirst();
//                for (int i = 0; i < c.getCount(); i++) {
//                    c.moveToPosition(i);
//                    int ser_id = Integer.valueOf(c.getString(c.getColumnIndex(AlbumsTable.SERID.toString())));
//                    String ser_category = c.getString(c.getColumnIndex(AlbumsTable.Category.toString()));
//                    String ser_channel = c.getString(c.getColumnIndex(AlbumsTable.Category.toString()));
//                    int cur_id = Integer.valueOf(c.getString(c.getColumnIndex(AlbumsTable.ID.toString())));
//                    if (ser_channel.equals(channel) && ser_category.equals(category)) {
//                        Album album = getAlbumByID(cur_id);
//                        return album;
//                    }
//                }
//            }
//        } catch (Exception e) {
//            Log.i(TAG, "Failed getAlbumByChannelCategory: " + e.toString());
//            return null;
//        }
//        return null;
//    }
//    public List<Album> getAlbumsByChannelAndCategory(String channel, String category) {
//        String DB_Table = Table_Albums;
//        List<Album> found = new ArrayList<Album>();
//        try {
//            Cursor c = mySqLiteDatabase.query(DB_Table, getColumns(DB_Table), null, null, null, null, null);
//            if (c.getCount() > 0) {
//                c.moveToFirst();
//                for (int i = 0; i < c.getCount(); i++) {
//                    c.moveToPosition(i);
//                    String ser_category = c.getString(c.getColumnIndex(AlbumsTable.Category.toString()));
//                    String ser_channel = c.getString(c.getColumnIndex(AlbumsTable.Channel.toString()));
//                    int cur_id = Integer.valueOf(c.getString(c.getColumnIndex(AlbumsTable.ID.toString())));
//                    if (ser_channel.equals(channel) && ser_category.equals(category)) {
//                        Album album = getAlbumByID(cur_id);
//                        found.add(album);
//                    }
//                }
//                return found;
//            }
//        } catch (Exception e) {
//            Log.i(TAG, "Failed getAlbumsByChannelCategory: " + e.toString());
//            return null;
//        }
//        return null;
//    }
//    public ArrayList<Album> getAllAlbums() {
//        String DB_Table = Table_Albums;
//        ArrayList<Album> found = new ArrayList<Album>();
//        try {
//            Cursor c = mySqLiteDatabase.query(DB_Table, getColumns(DB_Table), null, null, null, null, null);
//            c.moveToFirst();
//            for (int i = 0; i < c.getCount() && i<150; i++) {
//                c.moveToPosition(i);
//                int cur_id = Integer.valueOf(c.getString(c.getColumnIndex(AlbumsTable.ID.toString())));
//                Album album = getAlbumByID(cur_id);
//                if (album != null) {
//                    found.add(album);
//                }
//            }
//        } catch (Exception e) {
//            return null;
//        }
//        return found;
//    }
//    public ArrayList<Album> getNewlyAddedAlbums() {
//        String DB_Table = Table_Albums;
//        ArrayList<Album> found = new ArrayList<Album>();
//        try {
//            Cursor c = mySqLiteDatabase.query(DB_Table, getColumns(DB_Table), null, null, null, null, null);
//            c.moveToFirst();
//            for (int i = c.getCount()-1; i >= 0; i--) {
//                c.moveToPosition(i);
//                int cur_id = Integer.valueOf(c.getString(c.getColumnIndex(AlbumsTable.ID.toString())));
//                Album album = getAlbumByID(cur_id);
//                if (album != null && found.size() < 150) {
//                    found.add(album);
//                }
//            }
//            Collections.reverse(found);
//        } catch (Exception e) {
//            return null;
//        }
//        return found;
//    }
//    public ArrayList<Album> getAllMyAlbums() {
//        String DB_Table = Table_Favourite;
//        ArrayList<Album> found = new ArrayList<Album>();
//        try {
//            Cursor c = mySqLiteDatabase.query(DB_Table, getColumns(DB_Table), null, null, null, null, null);
//            c.moveToFirst();
//            for (int i = c.getCount()-1; i >= 0; i--) {
//                c.moveToPosition(i);
//                int cur_id = Integer.valueOf(c.getString(c.getColumnIndex(AlbumsTable.ID.toString())));
//                Album album = getMyAlbumByID(cur_id);
//                if (album != null && found.size() < 150) {
//                    found.add(album);
//                }
//            }
//            Collections.reverse(found);
//        } catch (Exception e) {
//            return null;
//        }
//        return found;
//    }
//    public List<Album> getAllAlbumsRandomly() {
//        String DB_Table = Table_Albums;
//        List<Album> found = new ArrayList<Album>();
//        List<Album> found1 = new ArrayList<Album>();
//        try {
//            Cursor c = mySqLiteDatabase.query(DB_Table, getColumns(DB_Table), null, null, null, null, null);
//            c.moveToFirst();
//            for (int i = c.getCount()-1; i >= 0; i--) {
//                c.moveToPosition(i);
//                int cur_id = Integer.valueOf(c.getString(c.getColumnIndex(AlbumsTable.ID.toString())));
//                Album album = getAlbumByID(cur_id);
//                if (album != null) {
//                    found.add(album);
//                }
//            }
//            if(found.size()>0){
//                Collections.shuffle(found);
//                if(found.size()>150){
//                    for(int i=0;i<150;i++){
//                        found1.add(found.get(i));
//                    }
//                    return found1;
//                }else {
//                    return found;
//                }
//            }
//        } catch (Exception e) {
//            return null;
//        }
//        return found;
//    }
//    public long addNewAlbum(Album tender){
//        ContentValues cv = new ContentValues();
//        cv.put(AlbumsTable.SERID.toString(),tender.getSerID());
//        cv.put(AlbumsTable.Title.toString(),tender.getTitle());
//        cv.put(AlbumsTable.ImageURL.toString(),tender.getImageURL());
//        cv.put(AlbumsTable.VideoURL.toString(),tender.getVideoURL());
//        cv.put(AlbumsTable.Channel.toString(),tender.getChannel());
//        cv.put(AlbumsTable.Category.toString(),tender.getCategory());
//        cv.put(AlbumsTable.PubDate.toString(),tender.getPubDate());
//        Album old_Album = getAlbumBySerID(tender.getSerID());
//        if(old_Album == null){
//            long x = addNew(Database.Table_Albums,cv);
//            if(x>0){
//                Log.i(TAG,"Successfully Added: Album -> \n"+cv.toString());
//            }else {
//                Log.i(TAG,"Error During Adding: Album -> \n"+cv.toString());
//            }
//            return x;
//        }else {
//            long x = updateByID(Table_Albums,cv,old_Album.getID());
//            if(x>0){
//                Log.i(TAG,"Successfully Updated: Album -> \n"+cv.toString());
//            }else {
//                Log.i(TAG,"Error During UPdated: Album -> \n"+cv.toString());
//            }
//            return x;
//        }
//    }
//    public long addNewFavouriteAlbum(Album tender){
//        ContentValues cv = new ContentValues();
//        cv.put(AlbumsTable.SERID.toString(),tender.getSerID());
//        cv.put(AlbumsTable.Title.toString(),tender.getTitle());
//        cv.put(AlbumsTable.ImageURL.toString(),tender.getImageURL());
//        cv.put(AlbumsTable.VideoURL.toString(),tender.getVideoURL());
//        cv.put(AlbumsTable.Channel.toString(),tender.getChannel());
//        cv.put(AlbumsTable.Category.toString(),tender.getCategory());
//        cv.put(AlbumsTable.PubDate.toString(),tender.getPubDate());
//        Album old_Album = getMyAlbumBySerID(tender.getSerID());
//        if(old_Album == null){
//            long x = addNew(Database.Table_Favourite,cv);
//            if(x>0){
//                Log.i(TAG,"Successfully Added: Favourite -> \n"+cv.toString());
//            }else {
//                Log.i(TAG,"Error During Adding: Favourite -> \n"+cv.toString());
//            }
//            return x;
//        }else {
//            long x = updateByID(Table_Favourite,cv,old_Album.getID());
//            if(x>0){
//                Log.i(TAG,"Successfully Updated: Favourite -> \n"+cv.toString());
//            }else {
//                Log.i(TAG,"Error During UPdated: Favourite -> \n"+cv.toString());
//            }
//            return x;
//        }
//    }
//
//
//    public String[] getAllChannels() {
//        String DB_Table = Table_Albums;
//        List<String> found = new ArrayList<String>();
//        try {
//            Cursor c = mySqLiteDatabase.query(DB_Table, getColumns(DB_Table), null, null, null, null, null);
//            if (c.getCount() > 0) {
//                c.moveToFirst();
//                for (int i = 0; i < c.getCount(); i++) {
//                    c.moveToPosition(i);
//                    String cur_channel = c.getString(c.getColumnIndex(AlbumsTable.Channel.toString()));
//                    if(!found.contains(cur_channel)){
//                        found.add(cur_channel);
//                    }
//                }
//                if(found.size()>0){
//                    String[] data = new String[found.size()];
//                    for(int i=0;i<found.size();i++){
//                        data[i] = found.get(i).toString();
//                    }
//                    return data;
//                }
//            }
//        } catch (Exception e) {
//            Log.i(TAG, "Failed getAlbumBySerID: " + e.toString());
//            return null;
//        }
//        return null;
//    }
//    public String[] getAllCategoryByChannel(String channel) {
//        String DB_Table = Table_Albums;
//        List<String> found = new ArrayList<String>();
//        try {
//            Cursor c = mySqLiteDatabase.query(DB_Table, getColumns(DB_Table), null, null, null, null, null);
//            if (c.getCount() > 0) {
//                c.moveToFirst();
//                for (int i = 0; i < c.getCount(); i++) {
//                    c.moveToPosition(i);
//                    String cur_channel = c.getString(c.getColumnIndex(AlbumsTable.Channel.toString()));
//                    String cur_category = c.getString(c.getColumnIndex(AlbumsTable.Category.toString()));
//                    if(cur_channel.equals(channel) && !found.contains(cur_category)){
//                        found.add(cur_category);
//                    }
//                }
//                if(found.size()>0){
//                    String[] data = new String[found.size()];
//                    for(int i=0;i<found.size();i++){
//                        data[i] = found.get(i);
//                    }
//                    return data;
//                }
//            }
//        } catch (Exception e) {
//            Log.i(TAG, "Failed getCategoryByChannel: " + e.toString());
//            return null;
//        }
//        return null;
//    }
//
//    ///#####################################################################################################
//    ///##########################################     Live Channels    ###########################################
//    ///#####################################################################################################
//    public LiveChannel getLiveChannelByID(int id){
//        String DB_Table = Table_LiveChannels;
//        try {
//            Cursor c = mySqLiteDatabase.query(DB_Table, getColumns(DB_Table), null, null, null, null, null);
//            if (c.getCount() > 0) {
//                c.moveToFirst();
//                for (int i = 0; i < c.getCount(); i++) {
//                    c.moveToPosition(i);
//                    int cur_id = Integer.valueOf(c.getString(c.getColumnIndex(AlbumsTable.ID.toString())));
//                    if (cur_id == id) {
//                        LiveChannel liveChannel = new LiveChannel();
//                        liveChannel.setID(Integer.valueOf(c.getString(c.getColumnIndex(LiveChannalesTable.ID.toString()))));
//                        liveChannel.setSerID(Integer.valueOf(c.getString(c.getColumnIndex(LiveChannalesTable.SERID.toString()))));
//                        liveChannel.setName(c.getString(c.getColumnIndex(LiveChannalesTable.Name.toString())));
//                        liveChannel.setLiveURL(c.getString(c.getColumnIndex(LiveChannalesTable.StreamURL.toString())));
//                        return liveChannel;
//                    }
//                }
//            }
//        } catch (Exception e) {
//            Log.i(TAG, "Failed getLiveChannelByID: " + e.toString());
//            return null;
//        }
//        return null;
//    }
//    public LiveChannel getLiveChannelBySerID(int id) {
//        String DB_Table = Table_LiveChannels;
//        try {
//            Cursor c = mySqLiteDatabase.query(DB_Table, getColumns(DB_Table), null, null, null, null, null);
//            if (c.getCount() > 0) {
//                c.moveToFirst();
//                for (int i = 0; i < c.getCount(); i++) {
//                    c.moveToPosition(i);
//                    int ser_id = Integer.valueOf(c.getString(c.getColumnIndex(LiveChannalesTable.SERID.toString())));
//                    int cur_id = Integer.valueOf(c.getString(c.getColumnIndex(LiveChannalesTable.ID.toString())));
//                    if (ser_id == id) {
//                        LiveChannel liveChannel = getLiveChannelByID(cur_id);
//                        return liveChannel;
//                    }
//                }
//            }
//        } catch (Exception e) {
//            Log.i(TAG, "Failed getLiveChannelBySerID: " + e.toString());
//            return null;
//        }
//        return null;
//    }
//    public String[] getAllLiveChannels() {
//        String DB_Table = Table_LiveChannels;
//        List<String> found = new ArrayList<String>();
//        try {
//            Cursor c = mySqLiteDatabase.query(DB_Table, getColumns(DB_Table), null, null, null, null, null);
//            if (c.getCount() > 0) {
//                c.moveToFirst();
//                for (int i = 0; i < c.getCount(); i++) {
//                    c.moveToPosition(i);
//                    String channel_name = c.getString(c.getColumnIndex(LiveChannalesTable.Name.toString()));
//                    found.add(channel_name);
//                }
//                if(found.size()>0){
//                    String[] data = new String[found.size()];
//                    for(int i=0;i<found.size();i++){
//                        data[i] = found.get(i);
//                    }
//                    return data;
//                }
//            }
//        } catch (Exception e) {
//            Log.i(TAG, "Failed getAllLiveChannels: " + e.toString());
//            return null;
//        }
//        return null;
//    }
//    public List<LiveChannel> getAll_LiveChannels() {
//        String DB_Table = Table_LiveChannels;
//        List<LiveChannel> found = new ArrayList<LiveChannel>();
//        try {
//            Cursor c = mySqLiteDatabase.query(DB_Table, getColumns(DB_Table), null, null, null, null, null);
//            if (c.getCount() > 0) {
//                c.moveToFirst();
//                for (int i = 0; i < c.getCount(); i++) {
//                    c.moveToPosition(i);
//                    int channel_id = Integer.valueOf(c.getString(c.getColumnIndex(LiveChannalesTable.ID.toString())));
//                    LiveChannel liveChannel = getLiveChannelByID(channel_id);
//                    found.add(liveChannel);
//                }
//            }
//            return found;
//        } catch (Exception e) {
//            Log.i(TAG, "Failed getAllLiveChannels: " + e.toString());
//            return null;
//        }
//    }
//    public long addNewLiveChannel(LiveChannel liveChannel){
//        ContentValues cv = new ContentValues();
//        cv.put(LiveChannalesTable.SERID.toString(),liveChannel.getSerID());
//        cv.put(LiveChannalesTable.Name.toString(),liveChannel.getName());
//        cv.put(LiveChannalesTable.StreamURL.toString(),liveChannel.getLiveURL());
//        LiveChannel old_LiveChannel = getLiveChannelBySerID(liveChannel.getSerID());
//        if(old_LiveChannel == null){
//            long x = addNew(Database.Table_LiveChannels,cv);
//            if(x>0){
//                Log.i(TAG,"Successfully Added: LiveChannel -> \n"+cv.toString());
//            }else {
//                Log.i(TAG,"Error During Adding: LiveChannel -> \n"+cv.toString());
//            }
//            return x;
//        }else {
//            long x = updateByID(Table_LiveChannels,cv,old_LiveChannel.getID());
//            if(x>0){
//                Log.i(TAG,"Successfully Updated: LiveChannel -> \n"+cv.toString());
//            }else {
//                Log.i(TAG,"Error During UPdated: LiveChannel -> \n"+cv.toString());
//            }
//            return x;
//        }
//    }
//
//
    private String[] getColumns(String DB_Table) {
        String[] strs = null;
        if (DB_Table == Table_Albums) {
            strs = getStrArrFromEnum(AlbumsTable.class);
        } else if (DB_Table == Table_Favourite) {
            strs = getStrArrFromEnum(AlbumsTable.class);
        }
        return strs;
    }

}
