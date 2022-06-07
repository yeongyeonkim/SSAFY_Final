
package restapi.persistence.service;

import java.util.List;

import restapi.persistence.model.Interview;
import restapi.persistence.model.User;

public interface UserService {
    public List<User> findAll();
    
    public int save(User user);
    
    public void update(User user);
    
    public void delete(long id); 
    
    public User findById(Long id);
    
    public User findByUserid(String uid);
    
    public User findByEmail(String email);

    public String findPassword(String email, String userid);
    
    public User signin(String userid, String password);

    public User dibs(long userid, long interviewid);
    
    public List<Interview> findAllDibs(long userid);
    
    public List<Interview> findAllInterviews(long userid);
    
    public boolean checkUserid(String userid);
    
    public boolean checkEmail(String email);

    public boolean changecheck(String email, String password);
}