-- select * from student_bio limit 25;

-- select * from student_award limit 25;

-- select bio.student_id, bio.first_name, bio.last_name, award.award_amount
-- from student_bio as bio
-- join student_award as award
-- on bio.student_id = award.student_id
-- limit 25;

-- select count(*) as bio_len from student_bio;

select count(*) as award_len from student_award;