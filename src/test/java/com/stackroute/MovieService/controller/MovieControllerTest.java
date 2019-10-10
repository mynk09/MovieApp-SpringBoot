package com.stackroute.MovieService.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.MovieService.domain.Movie;
import com.stackroute.MovieService.exception.GlobalExceptionHandler;
import com.stackroute.MovieService.exception.MovieAlreadyExistsException;
import com.stackroute.MovieService.exception.MovieNotFoundException;
import com.stackroute.MovieService.repository.MovieRepository;
import com.stackroute.MovieService.service.MovieService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@WebMvcTest
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private Movie movie;

    @MockBean
    private MovieRepository movieRepository;

    @MockBean
    private MovieService movieService;

    @InjectMocks
    private MovieController movieController;

    private List<Movie> list = null;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(movieController).setControllerAdvice(new GlobalExceptionHandler()).build();
        movie = new Movie();
        movie.setMovieId(101);
        movie.setMovieName("GOT");
        movie.setMovieComments("WINTER HAS GONE!!!");

        list = new ArrayList<>();
        list.add(movie);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void saveMovie() throws Exception {

        when(movieService.saveMovie(any())).thenReturn(movie);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/movie")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void saveMovieFailure() throws Exception {
        when(movieService.saveMovie(any())).thenThrow(MovieAlreadyExistsException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/movie")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void getAllMovies() throws Exception {
        when(movieService.getAllMovies()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getMovieById() throws Exception {
        when(movieService.getMovieById(anyInt())).thenReturn(movie);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/{id}",101)
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getMovieByIdFailure() throws Exception {
        when(movieService.getMovieById(anyInt())).thenThrow(MovieNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/{id}",100)
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }



    @Test
    public void getMoviesByName() throws Exception {
        when(movieService.getMoviesByName(any())).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/byName/{name}","Seven")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getMoviesByNameFailure() throws Exception {
        when(movieService.getMoviesByName(any())).thenThrow(MovieNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/byName/{name}","Se7en")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteMovie() throws Exception {
        when(movieService.deleteMovie(anyInt())).thenReturn(movie);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/movie/{id}","101")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }



    private static String asJsonString(final Object obj)
    {
        try{
            return new ObjectMapper().writeValueAsString(obj);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

}
