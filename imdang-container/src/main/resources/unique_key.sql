ALTER TABLE accuse ADD CONSTRAINT unique_accuse_1 UNIQUE (accuse_member_id, accused_insight_id);
ALTER TABLE recommend ADD CONSTRAINT unique_recommend_1 UNIQUE (recommend_member_id, recommended_insight_id);

ALTER TABLE member ADD CONSTRAINT unique_member_1 UNIQUE (nickname);
ALTER TABLE member ADD CONSTRAINT unique_member_2 UNIQUE (auth_id, auth_type, is_deleted);

ALTER TABLE terms_agreement ADD CONSTRAINT unique_terms_agreement_1 UNIQUE (member_id, terms_id);
