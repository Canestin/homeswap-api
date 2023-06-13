package com.homeSwap.homeswapbackend.service;

import com.homeSwap.homeswapbackend.DTO.user.*;
import com.homeSwap.homeswapbackend.exceptions.AuthenticationFailException;
import com.homeSwap.homeswapbackend.exceptions.CustomException;
import com.homeSwap.homeswapbackend.model.*;
import com.homeSwap.homeswapbackend.repository.*;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class UserService {
    private static final String MD5_ALGORITHM = "MD5";
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    HousingRepository housingRepository;

    @Autowired
    ConstraintRepository constraintRepository;

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    public SignUpDTO getDtoFromUsers(User user) {

        SignUpDTO signUpDTO = new SignUpDTO(user);
        return signUpDTO;
    }

    public List<SignUpDTO> listUsers() {
        List<User> users = userRepository.findAll();
        List<SignUpDTO> signUp = new ArrayList<>();
        for (User user : users) {
            signUp.add(getDtoFromUsers(user));
        }
        return signUp;
    }

    @Transactional // this allows both user creation and token generation/creation to be done
                   // together else reverse the user
    public ResponseDTO signUp(SignUpDTO signUpDTO, HttpServletRequest request)
            throws MessagingException, UnsupportedEncodingException {

        // let check if user email already exist
        if (Objects.nonNull(userRepository.findByEmail(signUpDTO.getEmail()))) {
            throw new CustomException("User already exist");
        }

        // this code would encript the password
        String encryptedPassword = signUpDTO.getPassword();

        encryptedPassword = hashingPassword(signUpDTO.getPassword());

        // email verification code generated
        // String email_verification_code = generateCode();
        // signUpDTO.setVerificationCode(email_verification_code);

        // This code would save the user

        User user = new User(signUpDTO.getFirstName(), signUpDTO.getLastName(), signUpDTO.getEmail(), encryptedPassword,
                signUpDTO.getPhoneNumber(), signUpDTO.getProfilePicture(), signUpDTO.getVerificationCode(),
                signUpDTO.isEnabled(), signUpDTO.getUserRole(), signUpDTO.getAddress(), signUpDTO.getCountry(),
                signUpDTO.getCity(), signUpDTO.getPostalCode(), signUpDTO.getCreatedDate());
        userRepository.save(user);
        // SendVerificationEmail(signUpDTO, getSiteURL(request));

        // this code would save the token at thesame time with the created user
        final AuthenticationToken authenticationToken = new AuthenticationToken(user);
        authenticationService.saveConfirmationToken(authenticationToken);

        ResponseDTO responseDTO = new ResponseDTO("success", "user successfully created");
        return responseDTO;
    }

    // this method would return the sign url path for email verification
    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    public void SendVerificationEmail(SignUpDTO signUpDTO, String siteURL)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = signUpDTO.getEmail();
        String fromAddress = "viqroy@gmail.com";
        String senderName = "HomeSwap";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "HomeSwap Team.";

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", signUpDTO.getFirstName() + " " + signUpDTO.getLastName());
        String verifyURL = siteURL + "/verify?code=" + signUpDTO.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        javaMailSender.send(message);

    }

    // This method would return string of hashed password for registration
    public static String hashingPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance(MD5_ALGORITHM);
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password using MD5", e);
        }
    }

    // This method would randomly generate password key codes
    public static String generateCode() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();

    }

    public SignInResponseDTO signIn(SignInDTO signInDTO) {

        // first find User by email
        User user = userRepository.findByEmail(signInDTO.getEmail());
        if (Objects.isNull(user)) {
            throw new AuthenticationFailException("invalid login credentials");
        }
        // check if password is right
        if (!user.getPassword().equals(hashingPassword(signInDTO.getPassword()))) {
            // passowrd doesnot match
            throw new AuthenticationFailException("invalid login credentials");
        }

        AuthenticationToken token = authenticationService.getToken(user);

        if (Objects.isNull(token)) {
            // token not present
            throw new CustomException("token not present");
        }

        return new SignInResponseDTO("success", token.getToken());
    }

    public SignUpDTO getUserById(Integer id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("user not found"));

        SignUpDTO signUpDTO = new SignUpDTO();
        signUpDTO.setId(user.getId());
        signUpDTO.setFirstName(user.getFirstName());
        signUpDTO.setLastName(user.getLastName());
        signUpDTO.setEmail(user.getEmail());
        signUpDTO.setPassword(user.getPassword());
        signUpDTO.setPhoneNumber(user.getPhoneNumber());
        signUpDTO.setProfilePicture(user.getProfilePicture());
        signUpDTO.setVerificationCode(user.getVerificationCode());
        signUpDTO.setEnabled(user.isEnabled());
        signUpDTO.setUserRole(user.getUserRole());
        signUpDTO.setAddress(user.getAddress());
        signUpDTO.setCountry(user.getCountry());
        signUpDTO.setCity(user.getCity());
        signUpDTO.setPostalCode(user.getPostalCode());
        signUpDTO.setCreatedDate(user.getCreatedDate());

        return signUpDTO;
    }

    // or this
    public Optional<User> findUserById(Integer userID) {
        return userRepository.findById(userID);
    }

    public void updateUser(Integer userID, User users) {
        User user = userRepository.findById(userID).get(); // this will get apartment id
        user.setFirstName(users.getFirstName());
        user.setLastName(users.getLastName());
        user.setPhoneNumber(users.getPhoneNumber());
        user.setProfilePicture(users.getProfilePicture());
        user.setAddress(users.getAddress());
        user.setCountry(users.getCountry());
        user.setCity(users.getCity());
        user.setPostalCode(users.getPostalCode());

        userRepository.save(user);

    }

    public void deleteUser(Integer userID) {

        List<housing> house = housingRepository.getHousingID(userID);

        int id = house.indexOf(0);

        constraintRepository.deleteById(id);
        serviceRepository.deleteById(id);
        housingRepository.deleteUserWithHousing(userID);

        tokenRepository.deleteById(userID);
        userRepository.deleteById(userID);

    }

    public boolean verify(String verificationCode) {
        User user = userRepository.findByVerificationCode(verificationCode);

        if (user == null || user.isEnabled()) {
            return false;
        } else {

            user.setVerificationCode(null);
            userRepository.enable(user.getId());

            return true;
        }

    }

    // to be deleted if not working

}
