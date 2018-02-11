package com.example.qrdz4162.pixformance.movies.presenter;

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
import io.reactivex.schedulers.TestScheduler;

/**
 * Created by qrdz4162 on 2/10/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class MoviePresenterTest {

    @Mock
    MovieView movieViewTest; //mocking the view layer

    @Mock
    MovieListRepo movieListRepoTest; //mocking the model layer

    @Mock
    List<MovieItem> movieItems;

    TestScheduler testScheduler;

    private MoviePresenter moviePresenterTest;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        // Mock scheduler using RxJava TestScheduler
        testScheduler = new TestScheduler();

        moviePresenterTest = new MoviePresenterImp(movieViewTest, movieListRepoTest,testScheduler , testScheduler);
    }

    @Test
    public void testLoadMovies(){
        String searchInputTest = "Batman";

        when(movieListRepoTest.getMovies(searchInputTest)).thenReturn(Observable.just(movieItems));
        moviePresenterTest.loadMovies(searchInputTest);
        testScheduler.triggerActions();

// verify(movieViewTest).hideProgressLoading();
// verify(movieViewTest).showProgressLoading();
    }

}
