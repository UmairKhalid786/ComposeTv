# ComposeTv

Android TV sample app built with Jetpack Compose for TV, Hilt, Ktor, and Media3.

## TMDB Login

The login screen uses TMDB's v3 user authentication flow.

### Requirements

- Configure a TMDB API key before running the app.
- Use a TMDB username and password on the login screen. TMDB does not accept email for this API flow.

You can provide the API key with either option:

```bash
export TMDB_API_KEY="your_api_key_here"
```

or:

```properties
# ~/.gradle/gradle.properties
tmdbApiKey=your_api_key_here
```

### How It Works

When the user signs in, the app calls TMDB in this order:

1. `POST /authentication/token/new` to create a request token.
2. `POST /authentication/token/validate_with_login` with the TMDB username, password, and request token.
3. `POST /authentication/session/new` to exchange the validated token for a TMDB `session_id`.

The resulting `session_id` is stored through the app's `UserSession` layer. After a successful login, the app navigates to the "Who Is Watching" screen and then into Home.

### Notes

- The app stores the TMDB session id, not the user's password.
- If the device is behind an HTTPS-intercepting proxy, debug builds may need a trusted user certificate.
- If the TMDB API key is missing, the login screen shows a configuration error instead of attempting authentication.

## Screens

### Login
<img width="1920" height="1080" alt="Screenshot_20251209_233857" src="https://github.com/user-attachments/assets/0f07b107-e16e-47c7-a96c-77f014b4e6d1" />

### Who is watching
<img width="1920" height="1080" alt="Screenshot_20251209_233910" src="https://github.com/user-attachments/assets/58edc4de-ae60-46db-bcb4-113d362f2c57" />

### Home Hero Item
<img width="1920" height="1080" alt="Screenshot_20251209_233923" src="https://github.com/user-attachments/assets/874af55a-a597-41fb-a68e-9fdeda308622" />

### Hero Item Focused
<img width="1920" height="1080" alt="Screenshot_20251209_233937" src="https://github.com/user-attachments/assets/e601570c-be69-49f9-a089-969620135b12" />

### Home Top Pick
<img width="1920" height="1080" alt="Screenshot_20251209_233951" src="https://github.com/user-attachments/assets/3c18c199-fa5c-4d03-9340-2e2e987a7cea" />

### Music
<img width="1920" height="1080" alt="Screenshot_20251209_234035" src="https://github.com/user-attachments/assets/f890838c-e782-4320-bc14-21c36d17d196" />

### Player Screen
<img width="1920" height="1080" alt="Screenshot_20251209_234011" src="https://github.com/user-attachments/assets/1050e033-3f0b-40c9-8511-8b2592c5fec1" />

### Movie Detail
<img width="1280" height="720" alt="Screenshot_20251211_154352" src="https://github.com/user-attachments/assets/7b3b8d55-6495-488f-af95-70799358cfe4" />

### Movie detail view more  
<img width="1280" height="720" alt="Screenshot_20251211_154409" src="https://github.com/user-attachments/assets/814d46c7-7496-4eba-8854-9f6230b24ae7" />
