-- 기부금 집행내역 초기 데이터
-- spring.sql.init.mode=always 설정 시 자동 실행됩니다.

INSERT INTO donation_records (date, recipient, category, amount, status)
SELECT '2025-04-15', '초록우산 어린이재단', '아동교육', 50000000, '집행완료'
WHERE NOT EXISTS (SELECT 1 FROM donation_records LIMIT 1);

INSERT INTO donation_records (date, recipient, category, amount, status)
VALUES
  ('2025-04-01', '굿네이버스',      '식품지원', 30000000, '집행완료'),
  ('2025-03-20', '사랑의열매',      '의료지원', 20000000, '집행완료'),
  ('2025-03-05', '지역아동센터연합', '교육환경', 15000000, '집행완료'),
  ('2025-05-01', '환경재단',        '환경보호', 10000000, '진행중'),
  ('2025-05-10', '노인복지관협회',   '노인복지',  8000000, '예정')
ON CONFLICT DO NOTHING;
