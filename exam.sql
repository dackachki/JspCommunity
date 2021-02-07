// 좋아요 수 구하기?
SELECT l.relId,SUM(`point`) FROM `like` AS l
LEFT JOIN article AS a
ON a.id = l.id
WHERE l.point = 1 AND l.relTypeCode = 'article'
GROUP BY l.relId
