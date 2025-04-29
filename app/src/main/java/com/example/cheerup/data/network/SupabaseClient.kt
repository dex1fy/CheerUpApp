package com.example.cheerup.data.network

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime

object SupabaseClient {
    val client: SupabaseClient = createSupabaseClient(
        supabaseUrl = "https://mwctxbnibzismnbgaknt.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im13Y3R4Ym5pYnppc21uYmdha250Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDU3OTQ0MDIsImV4cCI6MjA2MTM3MDQwMn0.tuhZY3MjkgFfw4k4-Tkow2PgqTaBpsz2NXTtOpWHuMk"
    ) {
        install(Postgrest)
        install(Auth)

    }
}