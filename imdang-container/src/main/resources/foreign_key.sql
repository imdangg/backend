ALTER TABLE accuse ADD CONSTRAINT fk_accuse_member_1 FOREIGN KEY (accuse_member_id) REFERENCES member(id) on delete restrict;
ALTER TABLE accuse ADD CONSTRAINT fk_accuse_member_2 FOREIGN KEY (accused_member_id) REFERENCES member(id) on delete restrict;
ALTER TABLE accuse ADD CONSTRAINT fk_accuse_insight_1 FOREIGN KEY (accused_insight_id) REFERENCES insight(id) on delete restrict;

ALTER TABLE exchange_request ADD CONSTRAINT fk_exchange_request_member_coupon_1 FOREIGN KEY (member_coupon_id) REFERENCES member_coupon(id) on delete restrict;
ALTER TABLE exchange_request ADD CONSTRAINT fk_exchange_request_member_snapshot_1 FOREIGN KEY (request_member_snapshot_id) REFERENCES member_snapshot(id) on delete restrict;
ALTER TABLE exchange_request ADD CONSTRAINT fk_exchange_request_snapshot_1 FOREIGN KEY (requested_snapshot_id) REFERENCES snapshot(id) on delete restrict;
ALTER TABLE exchange_request ADD CONSTRAINT fk_exchange_request_member_1 FOREIGN KEY (request_member_id) REFERENCES member(id) on delete restrict;
ALTER TABLE exchange_request ADD CONSTRAINT fk_exchange_request_insight_1 FOREIGN KEY (request_member_insight_id) REFERENCES insight(id) on delete restrict;
ALTER TABLE exchange_request ADD CONSTRAINT fk_exchange_request_insight_2 FOREIGN KEY (requested_insight_id) REFERENCES insight(id) on delete restrict;
ALTER TABLE exchange_request ADD CONSTRAINT fk_exchange_request_member_2 FOREIGN KEY (requested_member_id) REFERENCES member(id) on delete restrict;

ALTER TABLE insight ADD CONSTRAINT fk_insight_member_1 FOREIGN KEY (member_id) REFERENCES member(id) on delete restrict;

ALTER TABLE member_coupon ADD CONSTRAINT fk_member_coupon_member_1 FOREIGN KEY (member_id) REFERENCES member(id) on delete restrict;
ALTER TABLE member_coupon ADD CONSTRAINT fk_member_coupon_coupon_1 FOREIGN KEY (coupon_id) REFERENCES coupon(id) on delete restrict;

ALTER TABLE member_snapshot ADD CONSTRAINT fk_member_snapshot_snapshot_1 FOREIGN KEY (snapshot_id) REFERENCES snapshot(id) on delete restrict;
ALTER TABLE member_snapshot ADD CONSTRAINT fk_member_snapshot_insight_1 FOREIGN KEY (insight_id) REFERENCES insight(id) on delete restrict;
ALTER TABLE member_snapshot ADD CONSTRAINT fk_member_snapshot_member_1 FOREIGN KEY (member_id) REFERENCES member(id) on delete restrict;

ALTER TABLE notification ADD CONSTRAINT fk_notification_member_1 FOREIGN KEY (receiver_id) REFERENCES member(id) on delete restrict;

ALTER TABLE recommend ADD CONSTRAINT fk_recommend_member_1 FOREIGN KEY (recommend_member_id) REFERENCES member(id) on delete restrict;
ALTER TABLE recommend ADD CONSTRAINT fk_recommend_insight FOREIGN KEY (recommended_insight_id) REFERENCES insight(id) on delete restrict;
ALTER TABLE recommend ADD CONSTRAINT fk_recommend_member_2 FOREIGN KEY (recommended_member_id) REFERENCES member(id) on delete restrict;

ALTER TABLE snapshot ADD CONSTRAINT fk_snapshot_insight_1 FOREIGN KEY (insight_id) REFERENCES insight(id) on delete restrict;
ALTER TABLE snapshot ADD CONSTRAINT fk_snapshot_member_1 FOREIGN KEY (member_id) REFERENCES member(id) on delete restrict;

ALTER TABLE terms_agreement ADD CONSTRAINT fk_terms_agreement_member_1 FOREIGN KEY (member_id) REFERENCES member(id) on delete restrict;
ALTER TABLE terms_agreement ADD CONSTRAINT fk_terms_agreement_terms_1 FOREIGN KEY (terms_id) REFERENCES terms(id) on delete restrict;

