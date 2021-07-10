package com.company;

import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class MyURI implements Serializable {

    private final Pattern regex = Pattern.compile("(([a-z][a-zA-Z\\d+\\-.]+):\\/\\/((?:([a-zA-Z\\d\\-._~:!&$'\\(\\)*+,;=]+)@)([a-zA-Z\\d\\-\\._~!&$'\\(\\)*+,;=]+)(?::([\\d]+))?[\\/?#])?)?([:@\\-._~\\da-zA-Z!&$';=?\\/\\(\\)*+,;=]+\\/[:@\\-._~\\da-zA-Z!&$';=\\/\\(\\)*+,;=]*)(\\?[:@\\/?\\-._~\\da-zA-Z!&$'\\(\\)*+,;=]+)?(?:#(.+))?");
    private String scheme;
    private String authority;
    private String userInfo;
    private String host;
    private int port;
    private String path;
    private String query;
    private String fragment;


    public MyURI(String str) throws URISyntaxException {
        if (str == null) {
            throw new NullPointerException();
        }
        Matcher matcher = regex.matcher(str);
        if (matcher.find()) {
            scheme = matcher.group(2);
            authority = matcher.group(3);
            userInfo = matcher.group(4);
            host = matcher.group(5);
            port = Integer.parseInt(matcher.group(6));
            path = matcher.group(7);
            query = matcher.group(8);
            fragment = matcher.group(9);
        }
        else {
            throw new URISyntaxException(str, "Incorrect URI");
        }
    }

    public static MyURI create(String str) throws URISyntaxException{
        return new MyURI(str);
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        if (scheme != null) {
            str.append(scheme + "://");
            if (authority != null) {
                str.append(authority);
            }
        }
        str.append(path);
        if (query != null) {
            str.append(query);
        }
        if (fragment != null) {
            str.append("#" + fragment);
        }
        return str.toString();
    }

    public int getPort() {
        return port;
    }

    public String getPath() {
        return path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyURI myURI = (MyURI) o;
        return  port == myURI.port && regex.equals(myURI.regex) && scheme.equals(myURI.scheme)
                && authority.equals(myURI.authority)
                && userInfo.equals(myURI.userInfo) && host.equals(myURI.host) && path.equals(myURI.path)
                && query.equals(myURI.query) && fragment.equals(myURI.fragment);
    }

    @Override
    public int hashCode() {
        int res;
        if (authority != null && port != 0) {
            res = 31 * path.hashCode() * port + port + authority.hashCode();
        }
        res = 37*path.hashCode();
        return res;
    }

    public String getQuery() {
        return query;
    }

    public String getScheme() {
        return scheme;
    }

    public String getAuthority() {
        return authority;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public String getHost() {
        return host;
    }

    public String getFragment() {
        return fragment;
    }
}
