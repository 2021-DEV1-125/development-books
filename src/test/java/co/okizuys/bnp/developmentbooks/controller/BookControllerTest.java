package co.okizuys.bnp.developmentbooks.controller;

import co.okizuys.bnp.developmentbooks.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BookControllerTest extends AbstractControllerTest {

  private static final String BOOK_API_PATH = "/books";

  @Autowired private MockMvc mockMvc;

  @Autowired private BookService bookService;

  @Test
  void getAll() throws Exception {
    mockMvc
        .perform(get(BOOK_API_PATH).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }
}
