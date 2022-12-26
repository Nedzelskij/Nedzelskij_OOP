package MyClasses;

import javax.inject.Inject;

public class UserUser {
    private final AnotherSingleton dependency;

    @Inject
    public UserUser(AnotherSingleton bb) {
        this.dependency = bb;
    }

    public AnotherSingleton getDependency() {
        return dependency;
    }
}
