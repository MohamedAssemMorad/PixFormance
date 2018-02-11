package com.example.qrdz4162.pixformance.movies;

import com.example.qrdz4162.pixformance.movies.model.MovieListRepo;
import com.example.qrdz4162.pixformance.movies.model.entitiy.MovieItem;
import com.example.qrdz4162.pixformance.movies.model.entitiy.MovieResponse;
import com.example.qrdz4162.pixformance.movies.presenter.MoviePresenter;
import com.example.qrdz4162.pixformance.movies.presenter.MoviePresenterImp;
import com.example.qrdz4162.pixformance.movies.view.MovieView;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by qrdz4162 on 2/10/2018.
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class MoviePresenterTest {

    @Mock
    MovieView movieViewTest;

    @Mock
    MovieListRepo movieListRepoTest;

    @Mock
    List<MovieItem> movieItems;

    @Mock
    MovieResponse movieResponseTest;

    private MoviePresenter moviePresenterTest;


    @BeforeClass
    public static void onlyOnce() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        moviePresenterTest = new MoviePresenterImp(movieViewTest, movieListRepoTest,null);
        Schedulers.trampoline();
        Schedulers.trampoline();

        moviePresenterTest.onViewAttached(movieViewTest);
    }

    @Test
    public void testLoadMovies(){
        String searchInputTest = "Batman";

        when(movieListRepoTest.getMovies(searchInputTest)).thenReturn(Observable.just(movieItems));
        movieListRepoTest.getMovies(searchInputTest);
        moviePresenterTest.loadMovies(searchInputTest);

        verify(movieViewTest).showProgressLoading();
//        verify(movieViewTest).hideProgressLoading();

    }

}
