package BookMap.PentaRim.BookMap;


import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.Hashtag.Hashtag;
import BookMap.PentaRim.memo.Memo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMapAdapter;

import java.util.*;

@Component
@Getter
@Setter
public class BookMap {

    private Long userId;
    private String BookMapName;
    private Long bookMapId;
    private static Long serialNum = 0L;

    Long getSerialNum() {
        return serialNum;
    }

    @Component
    @Getter
    @Setter
    public class MapAndMemo {
        private ArrayList<Book> map;

        private List<Hashtag> hashtag;
        private Memo memo;
        private String type;

        public List<Hashtag> getHashtag() {
            if (hashtag == null) {
                return Collections.emptyList();
            }
            return hashtag;
        }

        @Autowired
        MapAndMemo(){}

        MapAndMemo(ArrayList<Book> map){
            this.map = map;
            this.type = "Map";
        }

        MapAndMemo(Memo memo){
            this.memo = memo;
            this.type = "Memo";
        }

        MapAndMemo(List<Hashtag> hashtag){
            this.hashtag = hashtag;
            this.type = "Hashtag";
        }
    }


    private ArrayList<MapAndMemo> bookMapIndex = new ArrayList<>();



    public BookMap(){ //생성 시 ID 증가
        bookMapId = serialNum;
        serialNum++;
    }

    //오버로딩 사용하여 객체 추가 메소드 이름 통일
    public void addObj(ArrayList<Book> map){
        MapAndMemo mapObj = new MapAndMemo(map);
        bookMapIndex.add(mapObj);
    }

    public void addObj(Memo memo){ //북맵에 메모 줄 추가
        MapAndMemo memoObj = new MapAndMemo(memo);
        bookMapIndex.add(memoObj);
    }
/*
    public void addObj(List<String> tags){
               try{
            ArrayList<Hashtag> hashtags = new ArrayList<>();

        //if (!tags.isEmpty() & tags != null) {

            //System.out.println("ddd" + hashtags.get(0).getContent());
            //System.out.println("ddd" + hashtags.get(1).getContent());

                MapAndMemo tagObj = new MapAndMemo();
                for (String tag : tags) {
                    Hashtag hashtag = new Hashtag();
                    hashtag.setContent(tag);
                    //System.out.println(hashtag.getContent());
                    hashtags.add(tags.indexOf(tag), hashtag);
                }
                tagObj.setHashtag(hashtags);
                bookMapIndex.add(tagObj);
                if(!bookMapIndex.isEmpty() & bookMapIndex != null) {
                    System.out.println("5555" + bookMapIndex.get(0).getHashtag().get(0));
                }
            }catch (NullPointerException e){
                System.out.println("null value found");
            }
        if (tags != null && !tags.isEmpty()) {
            ArrayList<Hashtag> hashtags = new ArrayList<>();
            MapAndMemo tagObj = new MapAndMemo();
            for (String tag : tags) {
                Hashtag hashtag = new Hashtag();
                hashtag.setContent(tag);
                hashtags.add(hashtag);
            }
            tagObj.setHashtag(hashtags);
            bookMapIndex.add(tagObj);
            if (!bookMapIndex.isEmpty()) {
                System.out.println("5555" + bookMapIndex.get(1).getHashtag().get(0).getContent());
            }
        } else {
            System.out.println("tags list is null or empty");
        }
    }



 */
    public void deleteObj(MapAndMemo mapAndMemo, int index) {
        bookMapIndex.remove(index);
        mapAndMemo = null; //임시
    }

    //북맵 내부 순서 변경
    public void changeIndex(int inputIndex, int outIndex){
        MapAndMemo changeObj = new MapAndMemo();
        changeObj = bookMapIndex.get(inputIndex);
        bookMapIndex.remove(inputIndex);
        bookMapIndex.add(outIndex, changeObj);
    }

}

