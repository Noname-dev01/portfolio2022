package portfolio2022.portfolio2022.dailyshop.security.config;

import java.util.Map;

public interface OAuth2UserInfo {

    String getProviderId();
    String getProvider();
    String getEmail();
    String getName();

    public class GoogleUserInfo implements OAuth2UserInfo{
        private Map<String, Object> attributes;

        public GoogleUserInfo(Map<String, Object> attributes) {
            this.attributes = attributes;
        }

        @Override
        public String getProviderId() {
            return String.valueOf(attributes.get("sub"));
        }

        @Override
        public String getProvider() {
            return "google";
        }

        @Override
        public String getEmail() {
            return String.valueOf(attributes.get("email"));
        }

        @Override
        public String getName() {
            return String.valueOf(attributes.get("name"));
        }
    }

    public class NaverUserInfo implements OAuth2UserInfo{
        private Map<String, Object> attributes;

        public NaverUserInfo(Map<String, Object> attributes) {
            this.attributes = attributes;
        }

        @Override
        public String getProviderId() {
            return String.valueOf(attributes.get("id"));
        }

        @Override
        public String getProvider() {
            return "naver";
        }

        @Override
        public String getEmail() {
            return String.valueOf(attributes.get("email"));
        }

        @Override
        public String getName() {
            return String.valueOf(attributes.get("name"));
        }
    }
}
