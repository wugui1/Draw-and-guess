package fzu.zrf.dng.both.io;

import java.io.Serializable;

@SuppressWarnings("serial")
public class LoginInfo implements Serializable {
    public final String name;
    public final String password;

    public LoginInfo(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LoginInfo other = (LoginInfo) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("LoginInfo [name=%s, password=%s]", name, password);
    }

    public static class Result implements Serializable {
        public final boolean success;
        public final int type;
        public final String auth;

        public Result(boolean success, int type, String auth) {
            this.success = success;
            this.type = type;
            this.auth = auth;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((auth == null) ? 0 : auth.hashCode());
            result = prime * result + (success ? 1231 : 1237);
            result = prime * result + type;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Result other = (Result) obj;
            if (auth == null) {
                if (other.auth != null)
                    return false;
            } else if (!auth.equals(other.auth))
                return false;
            if (success != other.success)
                return false;
            if (type != other.type)
                return false;
            return true;
        }

        @Override
        public String toString() {
            return String.format("Result [success=%s, type=%s, auth=%s]", success, type, auth);
        }
    }
}
