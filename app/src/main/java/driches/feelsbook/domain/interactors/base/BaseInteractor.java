package driches.feelsbook.domain.interactors.base;

public abstract class BaseInteractor implements Interactor{

    protected volatile boolean mIsExecuting;
    protected volatile boolean mIsStoped;

    public abstract void run();

    public void stop() {
        this.mIsExecuting = false;
        this.mIsStoped = true;
    }

    public boolean isExecuting() {
        return this.mIsExecuting;
    }

    public void finish() {
        this.mIsStoped = false;
        this.mIsExecuting = false;
    }

    @Override
    public void execute() {
        // Needs to be threaded
        this.run();
    }
}
