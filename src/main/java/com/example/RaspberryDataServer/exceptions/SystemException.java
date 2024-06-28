package com.example.RaspberryDataServer.exceptions;

public class SystemException  extends Exception  {
        private int internalCodeError;

        public SystemException(String message, int internalCodeError) {
            super(message);
            this.internalCodeError = internalCodeError;
        }

}
