import play.*;
import play.api.OptionalSourceMapper;
import play.api.UsefulException;
import play.api.routing.Router;
import play.http.DefaultHttpErrorHandler;
import play.mvc.Http.*;
import play.mvc.*;
import play.mvc.Http.RequestHeader;

import javax.inject.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class ErrorHandler extends DefaultHttpErrorHandler {

    @Inject
    public ErrorHandler(Configuration configuration, Environment environment,
                        OptionalSourceMapper sourceMapper, Provider<Router> routes) {
        super(configuration, environment, sourceMapper, routes);
    }

    protected CompletionStage<Result> onProdServerError(RequestHeader request, UsefulException exception) {
        return CompletableFuture.completedFuture(
                Results.internalServerError("A server error occurred: " + exception)
        );
    }

    protected CompletionStage<Result> onForbidden(RequestHeader request, String message) {
        return CompletableFuture.completedFuture(
                Results.forbidden("You're not allowed to access this resource.")
        );
    }

	/* (non-Javadoc)
	 * @see play.http.DefaultHttpErrorHandler#logServerError(play.mvc.Http.RequestHeader, play.api.UsefulException)
	 */
	@Override
	protected void logServerError(RequestHeader arg0, UsefulException arg1) {
		// TODO Auto-generated method stub
		super.logServerError(arg0, arg1);
	}

	/* (non-Javadoc)
	 * @see play.http.DefaultHttpErrorHandler#onBadRequest(play.mvc.Http.RequestHeader, java.lang.String)
	 */
	@Override
	protected CompletionStage<Result> onBadRequest(RequestHeader arg0,
			String arg1) {
		// TODO Auto-generated method stub
		return super.onBadRequest(arg0, arg1);
	}

	/* (non-Javadoc)
	 * @see play.http.DefaultHttpErrorHandler#onClientError(play.mvc.Http.RequestHeader, int, java.lang.String)
	 */
	@Override
	public CompletionStage<Result> onClientError(RequestHeader arg0, int arg1,
			String arg2) {
		// TODO Auto-generated method stub
		return super.onClientError(arg0, arg1, arg2);
	}

	/* (non-Javadoc)
	 * @see play.http.DefaultHttpErrorHandler#onDevServerError(play.mvc.Http.RequestHeader, play.api.UsefulException)
	 */
	@Override
	protected CompletionStage<Result> onDevServerError(RequestHeader arg0,
			UsefulException arg1) {
		// TODO Auto-generated method stub
		return super.onDevServerError(arg0, arg1);
	}

	/* (non-Javadoc)
	 * @see play.http.DefaultHttpErrorHandler#onNotFound(play.mvc.Http.RequestHeader, java.lang.String)
	 */
	@Override
	protected CompletionStage<Result> onNotFound(RequestHeader arg0, String arg1) {
		// TODO Auto-generated method stub
		return super.onNotFound(arg0, arg1);
	}

	/* (non-Javadoc)
	 * @see play.http.DefaultHttpErrorHandler#onOtherClientError(play.mvc.Http.RequestHeader, int, java.lang.String)
	 */
	@Override
	protected CompletionStage<Result> onOtherClientError(RequestHeader arg0,
			int arg1, String arg2) {
		// TODO Auto-generated method stub
		return super.onOtherClientError(arg0, arg1, arg2);
	}

	/* (non-Javadoc)
	 * @see play.http.DefaultHttpErrorHandler#onServerError(play.mvc.Http.RequestHeader, java.lang.Throwable)
	 */
	@Override
	public CompletionStage<Result> onServerError(RequestHeader arg0,
			Throwable arg1) {
		return CompletableFuture.completedFuture(
                Results.internalServerError("A server error occurred: " + arg1)
        );
	}
    
}