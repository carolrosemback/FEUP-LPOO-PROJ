package defender.state;

import defender.mvc.controller.GameController;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class StateTest {
    GameController gameController;
    DefenderContext context;

    @Before
    public void init() {
        gameController = mock(GameController.class);
        context = new DefenderContext(gameController);
    }

    @Test
    public void stateNext() {
        assertThat(context.getState(), instanceOf(Begin.class));
        context.nextState();

        assertThat(context.getState(), instanceOf(Running.class));
        context.nextState();

        assertThat(context.getState(), instanceOf(Over.class));
        context.nextState();

        assertThat(context.getState(), instanceOf(Running.class));
    }

    @Test
    public void beginExecuteTest() throws IOException {
        context.setState(new Begin());
        context.execute();
        verify(gameController, times(1)).beginController();
    }
    /*
    @Test(expected = IOException.class)
    public void beginExecuteWithExceotionTest() throws IOException {
        doThrow(new IOException()).when(gameController).beginController();
        context.setState(new Begin());
        context.execute();
    }
     */
    @Test
    public void runnigExecuteTest(){
        context.setState(new Running());
        context.execute();
        verify(gameController, times(1)).runnigController();
    }
    @Test
    public void overExecuteTest() throws IOException {
        context.setState(new Over());
        context.execute();
        verify(gameController, times(1)).overController();
    }



}
