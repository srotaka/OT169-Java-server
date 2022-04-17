package com.alkemy.ong.controller;
import com.alkemy.ong.dto.CategoryBasicDto;
import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.entity.News;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.NewsService;
import com.amazonaws.services.simplesystemsmanagement.model.ParameterNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(locations = "")
class NewsControllerTest {


    @Autowired
    private WebApplicationContext context;


    protected MockMvc mockMvc;

    @MockBean
    private NewsRepository newsRepository;

    @MockBean
    private NewsService newsService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private NewsController newsController;

    private News news;
    private NewsDto newsDto;
    private List<CategoryBasicDto> categories = new ArrayList<CategoryBasicDto>();
    private final static int PAGE = 0;
    private final static int SIZE = 2;
    private final String URL = "/news";


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        news = new News();
        news.setId("123abc");
        news.setName("name");
        news.setImage("image");
        news.setContent("content");
        news.setSoftDelete(false);


        newsDto = new NewsDto();
        newsDto.setName("name");
        newsDto.setImage("image");
        newsDto.setContent("content");
        categories.add(new CategoryBasicDto("name"));
        newsDto.setCategories(categories);

    }
    //POST//

    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("Save News Success (Code 201 Created) with role ADMIN")
    void createNews_Ok() throws Exception {
        when(newsService.save(newsDto)).thenReturn(newsDto);
        mockMvc.perform(post(URL)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newsDto))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isCreated());

    }

    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("Fail Saving News due to incomplete fields (Error 400 Bad Request)")
    void createNews_BadRequest() throws Exception {

        newsDto.setName(null);
        mockMvc.perform(post(URL)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newsDto)))
                .andExpect(status().isBadRequest());


    }

    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("Fail Saving News due to wrong role (Error 403 Forbidden)")
    void createNews_FailBecauseUserIsNotAdmin() throws Exception {
        when(newsService.save(newsDto)).thenReturn(newsDto);
        mockMvc.perform(post(URL)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newsDto))
                        .with(user("user").roles("USER"))
                        .with(csrf()))
                .andExpect(status().isForbidden());

    }

    @Test
    @DisplayName("Fail Saving News because user is not authenticated (Error 401 Unauthorized)")
    void createNews_FailBecauseUserIsAuthenticated() throws Exception {
        mockMvc.perform(post(URL)
                        .contentType(APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isUnauthorized());
    }
//GET//

    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("Get News Success (Code 201 Created) with role ADMIN")
    void getNewsById_Ok() throws Exception {
        String id = "123abc";
        when(newsService.getNewsById(id)).thenReturn(newsDto);

        mockMvc.perform(get("/news/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("Fail Getting News due to non-existent ID (Error 404 Not Found)")
    void getNewsById_NotFound() throws Exception {
        String idNotFound = "456def";

        when(newsService.getNewsById(idNotFound)).thenThrow((new ResponseStatusException(HttpStatus.NOT_FOUND)));
        mockMvc.perform(get(URL + "/" + idNotFound)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newsDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("Get All News Pages: Success (Code 200 OK)")
    void getAllPage_Ok() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(URL)
                        .contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("Get All News Pages: Success (Code 200 OK)")
    void getAllPage_Ok1() throws Exception {

         Map<String, Object> response = new LinkedHashMap<>();
          when(newsService.getAllPages(1)).thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders.get(URL + "?page=1")
                        .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(response)))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }


    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("Fail Getting All News Pages (Code 500 Internal Server Error)")
    void getAllPage_ServerError() throws Exception {
        Map<String, Object> response = new LinkedHashMap<>();
        when(newsService.getAllPages(0)).thenThrow(new Exception("Fail to load pages"));

        mockMvc.perform(MockMvcRequestBuilders.get(URL + "?page=0")
                        .contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }

    @Test
    @DisplayName("Fail Getting News because user is not authenticated (Error 401 Unauthorized)")
    void getNews_FailBecauseUserIsAuthenticated() throws Exception {
        String id = "123abc";
        when(newsService.getNewsById(id)).thenReturn(newsDto);

        mockMvc.perform(get("/news/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }


    //PUT//

    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("Update News: Success (Code 200 OK)")
    void updateNews_Ok() throws Exception {
        String id = "123abc";

        when(newsService.updateNews("123abc", newsDto)).thenReturn(newsDto);
        mockMvc.perform(put(URL + "/123abc")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newsDto))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isOk());

    }
    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("Fail Updating News due to non-existent ID (Error 404 Not Found)")
    void updateNews_NotFoundById() throws Exception {
        String idNotFound = "456def";
        newsDto.setName("New name");

        when(newsService.updateNews(idNotFound, newsDto)).thenThrow(new ParameterNotFoundException(""));

        mockMvc.perform(put(URL + idNotFound)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("Fail Updating News due to incorrect role (Error 403 Forbidden)")
    void updateNews_FailBecauseUserIsNotAdmin() throws Exception {
        when(newsService.updateNews("123abc", newsDto)).thenReturn(newsDto);
        mockMvc.perform(put(URL + "/123abc")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newsDto))
                        .with(user("user").roles("USER"))
                        .with(csrf()))
                .andExpect(status().isForbidden());

    }

    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("Fail Updating News due to incomplete fields (Error 400 Bad Request)")
    void updateNews_BadRequest() throws Exception {
        newsDto.setName(null);
        newsDto.setImage(null);
        newsDto.setContent(null);

        mockMvc.perform(put(URL + "/123abc")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newsDto))
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isBadRequest());

    }
    @Test
    @DisplayName("Fail Updating News because user is not authenticated (Error 401 Unauthorized)")
    void updateNews_FailBecauseUserIsNotAuthenticated() throws Exception {

        mockMvc.perform(put(URL+"/123abc")
                        .contentType(APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isUnauthorized());
    }


    //DELETE//

    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("Delete News: Success (Code 200 OK)")
    void delete_Ok() throws Exception {
        newsService.delete("123abc");

        mockMvc.perform(MockMvcRequestBuilders.delete(URL + "/123abc")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());


    }

    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("Fail Deleting News due to non-existent ID (Error 404 Not Found)")
    void delete_NotFound() throws Exception {
        String idNotFound = "456def";
        newsService.delete("456def");

        mockMvc.perform(MockMvcRequestBuilders.delete(URL + idNotFound))
                .andExpect(status().isNotFound());

    }

    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("Fail Deleting News due to incorrect user role (Error 403 Forbidden)")
    void delete_FailBecauseUserIsNotAdmin() throws Exception {
        newsService.delete("123abc");

        mockMvc.perform(MockMvcRequestBuilders.delete(URL + "/123abc")
                        .contentType(APPLICATION_JSON)
                        .with(user("user").roles("USER"))
                        .with(csrf()))
                .andExpect(status().isForbidden());

    }

    @Test
    @DisplayName("Fail Deleting News because user is not authenticated (Error 401 Unauthorized)")
    void deleteNews_FailBecauseUserIsNotAuthenticated() throws Exception {
        newsService.delete("abc123");
        mockMvc.perform(delete(URL + "/123abc")
                        .contentType(APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isUnauthorized());

    }
}
