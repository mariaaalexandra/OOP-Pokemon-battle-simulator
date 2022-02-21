package com.company.History;

public class FullDatabase {
    /* Fields */
    MomentoState state = null;

    /* Constructor */
    public FullDatabase() {}

    /* Setters */
    public void setState(MomentoState state) {
        this.state = state;
    }

    /* Methods */
    public MomentoState saveStateToMemento(){
        return new MomentoState(state);
    }

    public void getStateFromMemento(MomentoState Memento){
        state = Memento.getState();
    }
}
