package BookMap.PentaRim.service;


import BookMap.PentaRim.Book.ListBooks;
import org.springframework.http.*;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;


@Component
public class BookSearchServiceImpl implements BookSearchService {

    //
    //수정필요(현재는 기능만 구현)
    //나중에 키는 gitignore같은곳으로!

    @Override
    public ListBooks searchBooks(String keyword) {

        try {
            ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());
            final RestTemplate restTemplate = new RestTemplate(factory);


            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Authorization", "KakaoAK ee44c8bc43622b808da7c4aefa2e35b8");
            HttpEntity<?> httpEntity = new HttpEntity(httpHeaders);
            URI targetUrl = UriComponentsBuilder
                    .fromUriString("https://dapi.kakao.com/v3/search/book") //기본 url
                    .queryParam("query", keyword) //인자
                    .build()
                    .encode(StandardCharsets.UTF_8) //인코딩
                    .toUri();
            ResponseEntity<ListBooks> resultResponseEntity = restTemplate.exchange(targetUrl, HttpMethod.GET, httpEntity, ListBooks.class);
            return resultResponseEntity.getBody();
        } catch (HttpStatusCodeException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {

            }
        }
        throw new IllegalStateException("api 호출 실패");
    }

}