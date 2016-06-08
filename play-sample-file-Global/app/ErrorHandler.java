import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;
import javax.inject.Provider;

import play.Configuration;
import play.Environment;
import play.Logger;
import play.api.OptionalSourceMapper;
import play.api.routing.Router;
import play.http.DefaultHttpErrorHandler;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;
import play.mvc.Results;

public class ErrorHandler extends DefaultHttpErrorHandler {

    @Inject
    public ErrorHandler(Configuration configuration, Environment environment,
                        OptionalSourceMapper sourceMapper, Provider<Router> routes) {
        super(configuration, environment, sourceMapper, routes);
    }

	/* (non-Javadoc)
	 * @see play.http.DefaultHttpErrorHandler#onBadRequest(play.mvc.Http.RequestHeader, java.lang.String)
	 */
	@Override
	protected CompletionStage<Result> onBadRequest(RequestHeader arg0,
			String arg1) {
		Logger.info("ON BAD REQUEST");
		return CompletableFuture.completedFuture(Results.badRequest(views.html.badrequest.render("ON BAD REQUEST", arg1, 400)));
	}

	/* (non-Javadoc)
	 * @see play.http.DefaultHttpErrorHandler#onClientError(play.mvc.Http.RequestHeader, int, java.lang.String)
	 */
/*	@Override
	public CompletionStage<Result> onClientError(RequestHeader arg0, int arg1,
			String arg2) {
		// TODO Auto-generated method stub
		return CompletableFuture.completedFuture(Results.badRequest("client :" + arg2));
	}*/

	/* (non-Javadoc)
	 * @see play.http.DefaultHttpErrorHandler#onNotFound(play.mvc.Http.RequestHeader, java.lang.String)
	 */
	@Override
	protected CompletionStage<Result> onNotFound(RequestHeader arg0, String arg1) {
		Logger.info("ON NOT FOUND");
		return CompletableFuture.completedFuture(Results.notFound(views.html.notfound.render("ON NOT FOUND", arg1, 400)));
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