package BookMap.PentaRim.service;

import BookMap.PentaRim.Book.Book;
import BookMap.PentaRim.Book.BookPersonal;
import BookMap.PentaRim.Book.BookState;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Component;

//import static jdk.internal.org.objectweb.asm.Opcodes.NULL;


@Component
public class BookSavedImpl implements BookSaved{

    public static final String COLLECTION_NAME = "savedBook3";   //임의 컬렉션
    BookPersonal bookPersonal = new BookPersonal();


    @Override
    public void save(String id, Book book) throws Exception{

        bookPersonal.setBook(book);
        bookPersonal.setBookState(BookState.NOT);
        bookPersonal.setId(id);
        bookPersonal.setMemo(null);

        String isbn = book.getIsbn();
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> apiFuture =
                firestore.collection(COLLECTION_NAME).document(id)
                        .collection(isbn).document(isbn)
                        .set(bookPersonal);
    }


    @Override
    public void ChangeBookState(String id, String isbn, String state) throws Exception{
        Firestore firestore  = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection(COLLECTION_NAME).document(id).collection(isbn).document(isbn);
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();
        BookPersonal bookpersonal = documentSnapshot.toObject(BookPersonal.class);
        switch (state){
            case "done":
                bookpersonal.setBookState(BookState.DONE);
                break;
            case "wish":
                bookpersonal.setBookState(BookState.WISH);
                break;
            case "reading":
                bookpersonal.setBookState(BookState.READING);
                break;
            case "not":
                bookpersonal.setBookState(BookState.NOT);
                break;

        }
        ApiFuture<WriteResult> apiFuture2 =
                firestore.collection(COLLECTION_NAME)
                        .document(id)
                        .collection(isbn).document(isbn)
                        .set(bookpersonal);
    }

    /* 하나하나 변경할 경우
    @Override
    public void ReadComplete(String id, String isbn) throws Exception {
        Firestore firestore  = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection(COLLECTION_NAME).document(id).collection(isbn).document(isbn);
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();
        BookPersonal bookpersonal = documentSnapshot.toObject(BookPersonal.class);
        bookpersonal.setBookState(BookState.DONE);
        ApiFuture<WriteResult> apiFuture2 =
                firestore.collection(COLLECTION_NAME)
                        .document(id)
                        .collection(isbn).document(isbn)
                        .set(bookpersonal);
    }

    @Override
    public void want(String id, String isbn) throws Exception{
        Firestore firestore  = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection(COLLECTION_NAME).document(id).collection(isbn).document(isbn);
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();
        BookPersonal bookpersonal = documentSnapshot.toObject(BookPersonal.class);
        bookpersonal.setBookState(BookState.WISH);
        ApiFuture<WriteResult> apiFuture2 =
                firestore.collection(COLLECTION_NAME)
                        .document(id)
                        .collection(isbn).document(isbn)
                        .set(bookpersonal);
    }

     */
    /*
    public void ReadMemo(){

    }

     */
}
