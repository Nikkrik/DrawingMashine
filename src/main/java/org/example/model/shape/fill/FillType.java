package org.example.model.shape.fill;

public enum FillType {
    FILL{
        @Override
        public FillBehavior create(){
            return new Fill();
        }
    },
    NO_FILL{
        @Override
        public FillBehavior create(){
            return new NoFill();
        }
    };

    public abstract FillBehavior create();
}
