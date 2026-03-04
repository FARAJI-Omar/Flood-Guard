-- =============================================================================
-- FloodGuard MA — Historical Flood Events Seed Data (Morocco 2015–2025)
-- 43 events · 3–4 per year · Covers all major flood-prone regions
--   including Western Sahara (Laayoune, Smara, Dakhla)
--
-- HOW TO RUN:
--   pgAdmin → flood_guard database → Query Tool → paste → Execute (F5)
--
-- IMPORTANT:
--   Run this ONLY ONCE. To re-run, first delete existing events:
--   DELETE FROM flood_events;
-- =============================================================================

-- ─────────────────────────────────────────────
-- 2015
-- ─────────────────────────────────────────────

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Sebou River Flood 2015',
  '2015-02-12',
  'high',
  182.4,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-6.18 34.22, -5.95 34.18, -5.72 34.21, -5.51 34.28, -5.38 34.42, -5.44 34.58, -5.63 34.66, -5.88 34.71, -6.05 34.65, -6.15 34.52, -6.22 34.38, -6.18 34.22)))', 4326)
);

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Tensift Flash Flood 2015',
  '2015-11-03',
  'moderate',
  44.7,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-8.52 31.72, -8.31 31.68, -8.08 31.71, -7.95 31.82, -7.98 31.93, -8.18 31.97, -8.42 31.91, -8.55 31.82, -8.52 31.72)))', 4326)
);

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Taza Wadi Flood 2015',
  '2015-12-15',
  'low',
  36.2,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-4.38 34.02, -4.18 33.98, -3.98 34.01, -3.82 34.08, -3.81 34.22, -3.95 34.28, -4.15 34.26, -4.32 34.19, -4.40 34.10, -4.38 34.02)))', 4326)
);

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Draa Valley Flood 2015',
  '2015-09-30',
  'moderate',
  61.5,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-7.15 30.72, -6.98 30.68, -6.78 30.71, -6.62 30.80, -6.58 30.95, -6.65 31.08, -6.80 31.12, -6.98 31.08, -7.10 30.97, -7.18 30.84, -7.15 30.72)))', 4326)
);

-- ─────────────────────────────────────────────
-- 2016
-- ─────────────────────────────────────────────

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Moulouya Valley Flood 2016',
  '2016-01-20',
  'moderate',
  87.3,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-2.88 34.82, -2.65 34.78, -2.42 34.81, -2.22 34.90, -2.12 35.05, -2.18 35.18, -2.35 35.25, -2.58 35.22, -2.76 35.12, -2.87 34.98, -2.88 34.82)))', 4326)
);

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Beni Mellal Atlas Flood 2016',
  '2016-11-08',
  'high',
  96.1,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-6.68 32.12, -6.48 32.08, -6.28 32.11, -6.12 32.20, -6.08 32.35, -6.12 32.50, -6.25 32.58, -6.45 32.55, -6.62 32.45, -6.70 32.30, -6.68 32.12)))', 4326)
);

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Souss Valley Flood 2016',
  '2016-02-28',
  'low',
  71.8,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-9.42 30.22, -9.18 30.18, -8.92 30.21, -8.68 30.28, -8.52 30.42, -8.55 30.56, -8.72 30.62, -8.98 30.58, -9.22 30.52, -9.40 30.40, -9.45 30.30, -9.42 30.22)))', 4326)
);

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Chaouia Plain Flood 2016',
  '2016-12-05',
  'moderate',
  113.2,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-8.02 32.72, -7.75 32.68, -7.48 32.72, -7.22 32.82, -7.10 33.00, -7.15 33.18, -7.32 33.28, -7.58 33.25, -7.82 33.15, -7.98 32.98, -8.05 32.84, -8.02 32.72)))', 4326)
);

-- ─────────────────────────────────────────────
-- 2017
-- ─────────────────────────────────────────────

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Ourika Flash Flood 2017',
  '2017-07-17',
  'high',
  24.3,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-7.92 31.12, -7.88 31.08, -7.82 31.05, -7.72 31.08, -7.65 31.15, -7.62 31.25, -7.65 31.38, -7.72 31.45, -7.82 31.48, -7.90 31.42, -7.94 31.30, -7.93 31.20, -7.92 31.12)))', 4326)
);

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Gharb Plain Flood 2017',
  '2017-02-10',
  'moderate',
  151.6,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-6.28 34.12, -6.02 34.08, -5.75 34.12, -5.52 34.22, -5.42 34.38, -5.48 34.55, -5.65 34.62, -5.90 34.65, -6.12 34.58, -6.25 34.45, -6.30 34.28, -6.28 34.12)))', 4326)
);

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Guelmim Wadi Flash Flood 2017',
  '2017-10-22',
  'high',
  31.4,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-10.35 28.88, -10.18 28.78, -10.02 28.82, -9.88 28.92, -9.85 29.05, -9.92 29.15, -10.08 29.18, -10.25 29.12, -10.38 29.00, -10.35 28.88)))', 4326)
);

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Tafilalet Valley Flood 2017',
  '2017-09-15',
  'low',
  54.9,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-4.65 31.62, -4.45 31.58, -4.22 31.62, -4.08 31.72, -4.05 31.88, -4.12 32.02, -4.28 32.08, -4.48 32.05, -4.62 31.95, -4.68 31.78, -4.65 31.62)))', 4326)
);

-- ─────────────────────────────────────────────
-- 2018
-- ─────────────────────────────────────────────

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Sebou River Flood 2018',
  '2018-01-25',
  'high',
  203.7,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-6.05 34.32, -5.80 34.26, -5.52 34.30, -5.32 34.42, -5.28 34.60, -5.38 34.75, -5.58 34.80, -5.82 34.78, -6.02 34.68, -6.12 34.52, -6.10 34.40, -6.05 34.32)))', 4326)
);

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Guelmim Critical Flash Flood 2018',
  '2018-10-25',
  'critical',
  46.2,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-10.48 28.78, -10.28 28.70, -10.05 28.74, -9.88 28.85, -9.82 29.00, -9.88 29.16, -10.05 29.22, -10.28 29.18, -10.45 29.05, -10.52 28.90, -10.48 28.78)))', 4326)
);

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Moulouya Delta Flood 2018',
  '2018-02-18',
  'moderate',
  66.5,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-3.18 34.88, -2.98 34.82, -2.72 34.86, -2.55 34.98, -2.52 35.12, -2.62 35.25, -2.82 35.28, -3.02 35.22, -3.15 35.08, -3.20 34.98, -3.18 34.88)))', 4326)
);

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Souss-Massa Estuary Flood 2018',
  '2018-11-20',
  'moderate',
  57.1,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-9.82 30.12, -9.62 30.08, -9.42 30.12, -9.25 30.22, -9.18 30.35, -9.22 30.48, -9.38 30.52, -9.58 30.48, -9.75 30.38, -9.85 30.24, -9.82 30.12)))', 4326)
);

-- ─────────────────────────────────────────────
-- 2019
-- ─────────────────────────────────────────────

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Draa Valley Flood 2019',
  '2019-09-22',
  'moderate',
  72.4,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-7.25 30.62, -7.05 30.58, -6.82 30.62, -6.62 30.72, -6.52 30.88, -6.55 31.05, -6.68 31.12, -6.88 31.08, -7.05 30.98, -7.22 30.82, -7.28 30.70, -7.25 30.62)))', 4326)
);

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Taza Wadi Flood 2019',
  '2019-12-01',
  'low',
  39.8,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-4.45 33.97, -4.22 33.93, -4.00 33.97, -3.82 34.05, -3.78 34.18, -3.88 34.28, -4.08 34.32, -4.28 34.28, -4.42 34.18, -4.48 34.06, -4.45 33.97)))', 4326)
);

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Doukkala Coastal Flood 2019',
  '2019-01-14',
  'moderate',
  91.3,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-8.85 33.02, -8.62 32.98, -8.38 33.02, -8.18 33.12, -8.12 33.28, -8.18 33.42, -8.35 33.48, -8.58 33.45, -8.78 33.35, -8.88 33.18, -8.85 33.02)))', 4326)
);

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Tadla Plain Flood 2019',
  '2019-02-28',
  'low',
  76.5,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-6.88 32.32, -6.65 32.28, -6.42 32.32, -6.22 32.42, -6.15 32.58, -6.20 32.72, -6.38 32.78, -6.62 32.75, -6.80 32.65, -6.90 32.48, -6.88 32.32)))', 4326)
);

-- ─────────────────────────────────────────────
-- 2020
-- ─────────────────────────────────────────────

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Sebou River Flood 2020',
  '2020-01-30',
  'high',
  231.8,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-6.15 34.22, -5.85 34.16, -5.55 34.20, -5.28 34.32, -5.18 34.52, -5.22 34.72, -5.42 34.82, -5.70 34.85, -5.98 34.80, -6.15 34.68, -6.22 34.50, -6.20 34.34, -6.15 34.22)))', 4326)
);

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Laayoune Saguia Flash Flood 2020',
  '2020-09-07',
  'critical',
  56.3,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-13.52 26.88, -13.28 26.80, -13.02 26.84, -12.85 26.96, -12.80 27.12, -12.88 27.28, -13.08 27.35, -13.32 27.30, -13.50 27.18, -13.58 27.02, -13.52 26.88)))', 4326)
);

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Haouz Plain Flood 2020',
  '2020-02-15',
  'moderate',
  122.4,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-8.38 31.52, -8.12 31.46, -7.85 31.50, -7.65 31.62, -7.58 31.78, -7.62 31.92, -7.80 31.98, -8.05 31.95, -8.28 31.85, -8.40 31.70, -8.38 31.52)))', 4326)
);

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Rharb-Larache Flood 2020',
  '2020-12-20',
  'low',
  83.7,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-6.58 35.02, -6.35 34.98, -6.12 35.02, -5.95 35.12, -5.88 35.25, -5.92 35.38, -6.10 35.42, -6.32 35.38, -6.52 35.28, -6.62 35.15, -6.58 35.02)))', 4326)
);

-- ─────────────────────────────────────────────
-- 2021
-- ─────────────────────────────────────────────

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Ourika Flash Flood 2021',
  '2021-08-08',
  'critical',
  21.6,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-7.88 31.06, -7.82 31.02, -7.75 31.05, -7.68 31.12, -7.62 31.22, -7.60 31.32, -7.65 31.40, -7.72 31.45, -7.80 31.42, -7.86 31.34, -7.90 31.22, -7.89 31.12, -7.88 31.06)))', 4326)
);

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Souss-Massa Estuary Flood 2021',
  '2021-11-20',
  'moderate',
  62.8,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-9.75 30.12, -9.52 30.07, -9.30 30.11, -9.14 30.22, -9.10 30.36, -9.16 30.48, -9.32 30.53, -9.55 30.50, -9.72 30.40, -9.79 30.26, -9.75 30.12)))', 4326)
);

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Ziz Valley Flash Flood 2021',
  '2021-09-03',
  'high',
  47.5,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-4.98 31.52, -4.78 31.46, -4.55 31.50, -4.38 31.62, -4.32 31.78, -4.38 31.92, -4.55 31.98, -4.75 31.94, -4.92 31.82, -5.00 31.68, -4.98 31.52)))', 4326)
);

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Gharb South Flood 2021',
  '2021-01-18',
  'moderate',
  147.3,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-6.85 34.12, -6.58 34.07, -6.30 34.11, -6.08 34.22, -5.98 34.38, -6.02 34.55, -6.18 34.63, -6.42 34.62, -6.65 34.52, -6.80 34.38, -6.88 34.24, -6.85 34.12)))', 4326)
);

-- ─────────────────────────────────────────────
-- 2022
-- ─────────────────────────────────────────────

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Doukkala Coastal Flood 2022',
  '2022-02-18',
  'low',
  88.4,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-8.78 33.02, -8.55 32.97, -8.30 33.01, -8.12 33.12, -8.08 33.28, -8.15 33.42, -8.32 33.50, -8.55 33.47, -8.75 33.36, -8.82 33.20, -8.78 33.02)))', 4326)
);

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Smara Saguia Flash Flood 2022',
  '2022-10-15',
  'high',
  34.9,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-11.95 26.52, -11.75 26.45, -11.55 26.50, -11.42 26.62, -11.40 26.78, -11.48 26.92, -11.65 26.97, -11.85 26.92, -11.98 26.78, -12.00 26.63, -11.95 26.52)))', 4326)
);

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Saïs Plain Flood 2022',
  '2022-01-25',
  'moderate',
  103.6,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-5.38 33.82, -5.12 33.77, -4.85 33.81, -4.65 33.92, -4.58 34.08, -4.65 34.22, -4.85 34.28, -5.10 34.25, -5.32 34.14, -5.42 33.98, -5.38 33.82)))', 4326)
);

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Azilal High Atlas Flash Flood 2022',
  '2022-09-25',
  'critical',
  27.3,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-6.88 31.72, -6.72 31.65, -6.55 31.70, -6.42 31.82, -6.38 31.96, -6.45 32.08, -6.60 32.12, -6.78 32.08, -6.90 31.96, -6.92 31.82, -6.88 31.72)))', 4326)
);

-- ─────────────────────────────────────────────
-- 2023
-- ─────────────────────────────────────────────

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Beni Mellal Oued Flood 2023',
  '2023-03-12',
  'moderate',
  93.2,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-6.78 32.22, -6.55 32.17, -6.30 32.21, -6.12 32.32, -6.06 32.48, -6.12 32.62, -6.28 32.68, -6.52 32.65, -6.70 32.54, -6.80 32.38, -6.78 32.22)))', 4326)
);

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Al Haouz Amizmiz Flash Flood 2023',
  '2023-09-20',
  'critical',
  33.8,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-8.48 31.02, -8.32 30.95, -8.15 30.99, -8.02 31.10, -7.96 31.25, -8.00 31.40, -8.14 31.48, -8.32 31.45, -8.46 31.34, -8.52 31.18, -8.48 31.02)))', 4326)
);

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Taroudant Souss Flood 2023',
  '2023-11-08',
  'high',
  67.4,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-9.28 30.32, -9.05 30.27, -8.80 30.31, -8.60 30.42, -8.52 30.56, -8.58 30.68, -8.75 30.73, -8.98 30.70, -9.18 30.60, -9.30 30.46, -9.28 30.32)))', 4326)
);

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Moulouya Lower Basin Flood 2023',
  '2023-02-05',
  'moderate',
  97.8,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-2.98 34.92, -2.72 34.86, -2.45 34.90, -2.25 35.02, -2.18 35.18, -2.25 35.30, -2.45 35.35, -2.70 35.32, -2.90 35.20, -3.00 35.06, -2.98 34.92)))', 4326)
);

-- ─────────────────────────────────────────────
-- 2024
-- ─────────────────────────────────────────────

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Guelmim Wadi Noun Flash Flood 2024',
  '2024-08-16',
  'critical',
  51.7,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-10.55 28.68, -10.32 28.60, -10.08 28.65, -9.90 28.78, -9.84 28.95, -9.90 29.10, -10.08 29.17, -10.32 29.13, -10.52 29.00, -10.60 28.82, -10.55 28.68)))', 4326)
);

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Gharb Plain Flood 2024',
  '2024-01-28',
  'high',
  198.5,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-6.75 34.22, -6.48 34.16, -6.20 34.20, -5.95 34.30, -5.82 34.48, -5.85 34.65, -6.02 34.72, -6.28 34.72, -6.52 34.62, -6.70 34.48, -6.78 34.32, -6.75 34.22)))', 4326)
);

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Dakhla Wadi Flash Flood 2024',
  '2024-09-12',
  'high',
  41.2,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-16.18 23.52, -15.98 23.45, -15.75 23.50, -15.60 23.62, -15.58 23.78, -15.65 23.90, -15.82 23.95, -16.02 23.90, -16.18 23.78, -16.22 23.62, -16.18 23.52)))', 4326)
);

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Essaouira Coastal Flood 2024',
  '2024-12-03',
  'moderate',
  58.6,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-10.18 31.32, -9.95 31.27, -9.72 31.31, -9.54 31.42, -9.48 31.56, -9.54 31.68, -9.70 31.73, -9.92 31.70, -10.12 31.60, -10.22 31.46, -10.18 31.32)))', 4326)
);

-- ─────────────────────────────────────────────
-- 2025
-- ─────────────────────────────────────────────

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Sebou River Flood 2025',
  '2025-01-25',
  'high',
  214.3,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-6.65 34.22, -6.38 34.16, -6.10 34.20, -5.85 34.30, -5.70 34.48, -5.72 34.66, -5.90 34.75, -6.15 34.76, -6.40 34.68, -6.58 34.52, -6.68 34.36, -6.65 34.22)))', 4326)
);

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Ourika Flash Flood 2025',
  '2025-08-14',
  'critical',
  19.8,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-7.85 31.07, -7.80 31.02, -7.72 31.05, -7.65 31.12, -7.60 31.22, -7.58 31.33, -7.62 31.42, -7.70 31.47, -7.80 31.44, -7.86 31.36, -7.87 31.24, -7.86 31.14, -7.85 31.07)))', 4326)
);

INSERT INTO flood_events (name, event_date, severity, area_km2, source, geom)
VALUES (
  'Smara Flash Flood 2025',
  '2025-10-02',
  'moderate',
  29.6,
  'Manual',
  ST_GeomFromText('MULTIPOLYGON(((-11.92 26.52, -11.72 26.46, -11.52 26.51, -11.40 26.63, -11.38 26.78, -11.46 26.92, -11.63 26.97, -11.83 26.93, -11.96 26.80, -11.98 26.64, -11.92 26.52)))', 4326)
);

-- =============================================================================
-- Verification queries — run after inserting to confirm everything is correct
-- =============================================================================

-- Total count (expect 43)
-- SELECT COUNT(*) FROM flood_events;

-- All events ordered by date
-- SELECT id, name, event_date, severity, area_km2, ST_IsValid(geom) AS valid
-- FROM flood_events
-- ORDER BY event_date;

-- Count per year
-- SELECT EXTRACT(YEAR FROM event_date) AS year, COUNT(*) AS total
-- FROM flood_events
-- GROUP BY year ORDER BY year;

-- Count per severity
-- SELECT severity, COUNT(*) FROM flood_events GROUP BY severity ORDER BY severity;
