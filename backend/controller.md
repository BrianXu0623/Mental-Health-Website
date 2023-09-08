
# Login/Signup
1. Login(username or email, password)
2. Sign Up(email, username, password, birthday, phonenumber)
3. Logout 

# Administration
1. mute_user(user_id)
2. force_delete_thread(thread_id)

# Post Thread
1. Create_thread(user_id, title, content, tags)
2. Delete_thread(user_id, thread_id)

# Comment on Thread
1. Comment on thread(user_id, thread_id, comment)
2. Edit thread comment(user_id, thread_id, comment)
3. Delete thread comment(user_id, thread_id)

# Linking Thread With Tags
1. link_tag_to_thread(thread_id, tag)
2. link_tags_to_thread(thread_id, list of tags)

# Following Others
1. follow_user(current_user_id, followed_user_id)

# Different Tier of Users
1. set_user_type(user_id, user_type) # Admin can only be changed in the database

# Information Page
1. set_threads_on_info_page(list of thread ids)
2. add_thread_on_info_page(thread_id)
3. remove_thread_on_info_page(thread_id)

# Search_Function
1. search_by_title(title)
2. search_by_title_with_filters(title, list of filters)

# Profile Management
1. edit_user(User Object)

# Thread Management
1. force_thread_edit(admin_id, thread_id, content)

# Appointment Management
1. Make_appointment(
    user_id, professional_id,
    symptom description, 
    appointment_datetime
)
2. Complete_appointment(professional_id, appointment_id)
3. Cancel_appoitnment(user_id, appointment_id, reason)

# User Feedback on Professional
1. rate_professional(user_id, professional_id, rating)
2. comment_on_professioal(user_id, professional_id, comment)
